package by.org.cgm.magneticobserver.ui.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.BindString;
import by.org.cgm.magneticobserver.AppCache;
import by.org.cgm.magneticobserver.R;
import by.org.cgm.magneticobserver.data.BarDataHelper;
import by.org.cgm.magneticobserver.data.DataProcessing;
import by.org.cgm.magneticobserver.data.LineDataHelper;
import by.org.cgm.magneticobserver.model.Data;
import by.org.cgm.magneticobserver.model.Mark;
import by.org.cgm.magneticobserver.model.response.GetDataResponse;
import by.org.cgm.magneticobserver.network.API;
import by.org.cgm.magneticobserver.util.DateTimeUtils;
import by.org.cgm.magneticobserver.util.StringUtils;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * A simple {@link BaseFragment} subclass.
 */
public class ChartsFragment extends BaseFragment {

    @Bind(R.id.fragment_charts__chart1) LineChart mChartLc1;
    @Bind(R.id.fragment_charts__chart2) BarChart mChartBc2;
    private ProgressDialog mProgressDialog;
    private ArrayList<Mark> mMarks = new ArrayList<>();
    @BindString(R.string.line_chart_H) String mX;

    private ValueFormatter integerFormatter = new ValueFormatter() {
        @Override
        public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
            return String.valueOf((int)value);
        }
    };

    public ChartsFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_charts;
    }

    @Override
    protected void readArgs(Bundle args) { /*do nothing*/ }

    @Override
    protected void initViews() {
        if (AppCache.getInstance().isNeededToUpdate() || AppCache.getInstance().isDataEmpty()) {
            mProgressDialog =
                    ProgressDialog.show(getActivity(), StringUtils.EMPTY, getString(R.string.loading));
            String day = DateTimeUtils.getYesterday();
            API.getInstance().getService().getDataRequest(day, new GetDataCallback());
            AppCache.getInstance().setNeededToUpdate(false);
        } else {
            calculateAndShowCharts();
        }
    }

    private void calculateAndShowCharts() {
        Mark.reset();
        DataProcessing dataProcessing = new DataProcessing();
        dataProcessing.calculate();
        mMarks = dataProcessing.getMagMarks();
        setDataForLineChart();
        setDataForBarChart();
    }

    private void setDataForLineChart() {
        ArrayList<Data> d = AppCache.getInstance().getData();
        LineDataHelper helper = new LineDataHelper(d, mX);
        mChartLc1.setData(helper.getLineData());
        if (isAdded()) mChartLc1.setDescription(getString(R.string.line_chart_desc));
        mChartLc1.setHighlightEnabled(false);
        mChartLc1.animateX(2500);
    }

    private void setDataForBarChart() {
        BarDataHelper helper = new BarDataHelper(mMarks, integerFormatter);
        mChartBc2.setData(helper.getBarData());
        if (isAdded()) mChartBc2.setDescription(getString(R.string.bar_chart_desc));
        mChartBc2.setHighlightEnabled(false);
        mChartBc2.animateXY(2500, 2500);
    }

    class GetDataCallback implements Callback<GetDataResponse> {

        @Override
        public void success(GetDataResponse getDataResponse, Response response) {
            AppCache.getInstance().setData(getDataResponse);
            API.getInstance().getService().getMiddleRequest(new GetMiddleCallback());
        }

        @Override
        public void failure(RetrofitError error) {
            mProgressDialog.dismiss();
            Log.d(GetDataCallback.class.getSimpleName(), error.getMessage());
            Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    class GetMiddleCallback implements Callback<GetDataResponse> {

        @Override
        public void success(GetDataResponse getDataResponse, Response response) {
            mProgressDialog.dismiss();
            AppCache.getInstance().setMiddle(getDataResponse);
            calculateAndShowCharts();
        }

        @Override
        public void failure(RetrofitError error) {
            mProgressDialog.dismiss();
            Log.d(GetMiddleCallback.class.getSimpleName(), error.getMessage());
            Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
