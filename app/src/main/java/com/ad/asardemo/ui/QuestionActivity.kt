package com.ad.asardemo.ui

import android.graphics.Color
import android.graphics.DashPathEffect
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.ad.asardemo.R
import com.ad.asardemo.databinding.ActivityQuestionBinding
import com.ad.asardemo.ui.adapter.ActivityAdapter
import com.github.mikephil.charting.components.LimitLine
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet

class QuestionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQuestionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.toolbar.setNavigationOnClickListener { finish() }
        initPieChart()
        initLineChart()
        initActivityList()
    }

    private fun initPieChart() {
        val pieEntries = ArrayList<PieEntry>()

        //initializing data
        val typeAmountMap: MutableMap<String, Float> = HashMap()
        typeAmountMap["Yes"] = 64.0F
        typeAmountMap["No"] = 36.0F

        //initializing colors for the entries
        val colors = ArrayList<Int>()
        colors.add(Color.parseColor("#144cc7"))
        colors.add(Color.parseColor("#06c270"))


        //input data and fit data into pie chart entry
        for (type in typeAmountMap.keys) {
            pieEntries.add(PieEntry(typeAmountMap[type] ?: 0F, type))
        }

        //collecting the entries with label name
        val pieDataSet = PieDataSet(pieEntries, "type")
        //setting text size of the value
        pieDataSet.valueTextSize = 12f
        //providing color list for coloring different entries
        pieDataSet.colors = colors
        //grouping the data set from entry to chart
        val pieData = PieData(pieDataSet)
        //showing the value of the entries, default true if not set
        pieData.setDrawValues(true)

        binding.chartMarketPredict.setData(pieData)
        binding.chartMarketPredict.invalidate()
    }

    private fun initLineChart() {
        val llXAxis = LimitLine(10f, "Index 10")
        llXAxis.lineWidth = 4f
        llXAxis.enableDashedLine(10f, 10f, 0f)
        llXAxis.labelPosition = LimitLine.LimitLabelPosition.RIGHT_BOTTOM
        llXAxis.textSize = 10f

        val xAxis: XAxis = binding.marketTrendChart.xAxis
        xAxis.axisMaximum = 3f
        xAxis.axisMinimum = 0f

        val xAxisLabel = ArrayList<String>()
        xAxisLabel.add("01 : 30 AM")
        xAxisLabel.add("01 : 30 AM")
        xAxisLabel.add("01 : 30 AM")
        xAxisLabel.add("01 : 30 AM")
        xAxis.valueFormatter = IndexAxisValueFormatter(xAxisLabel)
        xAxis.position = XAxis.XAxisPosition.BOTTOM

        val leftAxis: YAxis = binding.marketTrendChart.axisLeft
        leftAxis.removeAllLimitLines()

        leftAxis.axisMaximum = 100f
        leftAxis.axisMinimum = 0f
        leftAxis.setDrawZeroLine(false)
        leftAxis.setDrawLimitLinesBehindData(false)

        binding.marketTrendChart.axisRight.isEnabled = false
        binding.marketTrendChart.xAxis.setDrawGridLines(true)
        binding.marketTrendChart.xAxis.gridColor = Color.GRAY
        binding.marketTrendChart.axisLeft.textColor = Color.GRAY
        binding.marketTrendChart.xAxis.textColor = Color.GRAY
        binding.marketTrendChart.legend.textColor = Color.GRAY

        val dataSets = ArrayList<ILineDataSet>()
        dataSets.add(getReachData())
        val data = LineData(dataSets)
        binding.marketTrendChart.setData(data)

    }

    private fun getReachData(): LineDataSet {
        val values = ArrayList<Entry>()

        val set1: LineDataSet
        if (binding.marketTrendChart.data != null &&
            binding.marketTrendChart.data.getDataSetCount() > 0
        ) {
            set1 = binding.marketTrendChart.data.getDataSetByIndex(0) as LineDataSet
            set1.setValues(values)
            binding.marketTrendChart.data.notifyDataChanged()
            binding.marketTrendChart.notifyDataSetChanged()
        } else {
            set1 = LineDataSet(values, "Yes")
            set1.setDrawIcons(false)
            set1.enableDashedLine(10f, 5f, 0f)
            set1.enableDashedHighlightLine(10f, 5f, 0f)
            set1.color = Color.DKGRAY
            set1.setCircleColor(Color.DKGRAY)
            set1.lineWidth = 1f
            set1.circleRadius = 3f
            set1.setDrawCircleHole(false)
            set1.valueTextSize = 9f
            set1.setDrawFilled(true)
            set1.formLineWidth = 1f
            set1.formLineDashEffect = DashPathEffect(floatArrayOf(10f, 5f), 0f)
            set1.formSize = 15f

        }
        return set1
    }

    private fun initActivityList() {
        val adapter = ActivityAdapter()
        binding.recycleActivity.adapter = adapter
        binding.recycleActivity.layoutManager = LinearLayoutManager(this@QuestionActivity)
        adapter.submitData(arrayListOf("", "", "", ""))
    }
}