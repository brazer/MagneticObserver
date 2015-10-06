package by.org.cgm.magneticobserver.ui;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

import butterknife.Bind;
import by.org.cgm.magneticobserver.AppCache;
import by.org.cgm.magneticobserver.DataProcessing;
import by.org.cgm.magneticobserver.R;
import by.org.cgm.magneticobserver.models.Data;
import by.org.cgm.magneticobserver.models.Mark;
import by.org.cgm.magneticobserver.models.response.GetDataResponse;
import by.org.cgm.magneticobserver.network.API;
import by.org.cgm.magneticobserver.utils.StringUtils;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChartsFragment extends BaseFragment {

    @Bind(R.id.fragment_charts__chart1) LineChart mChartLc1;
    @Bind(R.id.fragment_charts__chart2) LineChart mChartLc2;
    private ProgressDialog mProgressDialog;
    private ArrayList<Data> data = new ArrayList<>();
    private ArrayList<Mark> marks = new ArrayList<>();

    public ChartsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_charts, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mProgressDialog =
                ProgressDialog.show(getActivity(), StringUtils.EMPTY, getString(R.string.loading));
        API.getInstance().getService().getDataRequest(new GetDataCallback());
    }

    private void setData() {
        ArrayList<String> xVals = new ArrayList<>();
        ArrayList<Entry> y1Vals = new ArrayList<>();
        ArrayList<Entry> y2Vals = new ArrayList<>();
        ArrayList<Entry> y3Vals = new ArrayList<>();
        ArrayList<Double> x = new ArrayList<>();
        ArrayList<Double> y = new ArrayList<>();
        ArrayList<Double> z = new ArrayList<>();
        for (Data d : data) {
            x.add(d.getX());
            y.add(d.getY());
            z.add(d.getZ());
        }
        float avrX = getAverage(x);
        float avrY = getAverage(y);
        float avrZ = getAverage(z);
        for (int i = 0; i < data.size(); i++) {
            xVals.add(
                    data.get(i).getDate() + " " +
                            data.get(i).getHour() + ":" + data.get(i).getMinute());
            float val = (float) data.get(i).getX() - avrX;
            y1Vals.add(new Entry(val, i));
            val = (float) data.get(i).getY() - avrY;
            y2Vals.add(new Entry(val, i));
            val = (float) data.get(i).getZ() - avrZ;
            y3Vals.add(new Entry(val, i));
        }
        ArrayList<LineDataSet> dataSets = new ArrayList<>();
        dataSets.add(getDataSet(y1Vals, "По компоненте X", Color.RED));
        dataSets.add(getDataSet(y2Vals, "По компоненте Y", Color.BLUE));
        dataSets.add(getDataSet(y3Vals, "По компоненте Z", Color.GREEN));
        LineData d = new LineData(xVals, dataSets);
        mChartLc2.setData(d);
        mChartLc2.invalidate();
    }

    private LineDataSet getDataSet(ArrayList<Entry> yVals, String name, int color) {
        LineDataSet set = new LineDataSet(yVals, name);
        set.disableDashedLine();
        set.setColor(color);
        set.setLineWidth(1f);
        set.setDrawCircles(false);
        set.setDrawCircleHole(false);
        set.setDrawValues(false);
        set.setFillAlpha(65);
        set.setFillColor(Color.BLACK);
        return set;
    }

    private float getAverage(ArrayList<Double> vals) {
        if (vals.size()==0) return 0;
        float sum = 0;
        for (double v : vals) sum += v;
        return sum/vals.size();
    }

    class GetDataCallback implements Callback<GetDataResponse> {

        @Override
        public void success(GetDataResponse getDataResponse, Response response) {
            Log.d(GetDataCallback.class.getSimpleName(), "success");
            data = getDataResponse;
            AppCache.getInstance().setData(getDataResponse);
            setData();
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
            Log.d(GetMiddleCallback.class.getSimpleName(), "success");
            AppCache.getInstance().setMiddle(getDataResponse);
            DataProcessing dataProcessing = new DataProcessing();
            dataProcessing.calculate();
            marks = dataProcessing.getMagMarks();
        }

        @Override
        public void failure(RetrofitError error) {
            mProgressDialog.dismiss();
            Log.d(GetMiddleCallback.class.getSimpleName(), error.getMessage());
            Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
