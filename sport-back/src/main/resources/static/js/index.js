/**
 * Created by xuxueli on 17/4/24.
 */
$(function () {

    // filter Time
    var rangesConf = {};

    $('#filterTime').daterangepicker({
        autoApply:false,
        singleDatePicker:false,
        showDropdowns:false,        // 是否显示年月选择条件
        timePicker: true, 			// 是否显示小时和分钟选择条件
        timePickerIncrement: 10, 	// 时间的增量，单位为分钟
        timePicker24Hour : true,
        opens : 'left', //日期选择框的弹出位置
        ranges: rangesConf,
        locale : {
            format: 'YYYY-MM-DD HH:mm:ss',
            separator : ' - ',
          // '一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'
            firstDay : 1
        },

    }, function (start, end, label) {
        freshChartDate(start, end);
    });


    /**
     * fresh Chart Date
     *
     * @param startDate
     * @param endDate
     */
    function freshChartDate(startDate, endDate) {
        $.ajax({
            type : 'POST',
            url : base_url + '/chartInfo',
            data : {
                'startDate':startDate.format('YYYY-MM-DD HH:mm:ss'),
                'endDate':endDate.format('YYYY-MM-DD HH:mm:ss')
            },
            dataType : "json",
            success : function(data){
                if (data.code == 200) {
                    lineChartInit(data)
                    pieChartInit(data);
                } else {
                    layer.open({
                        title: I18n.system_tips ,
                        btn: [ I18n.system_ok ],
                        content: (data.msg || I18n.job_dashboard_report_loaddata_fail ),
                        icon: '2'
                    });
                }
            }
        });
    }

    /**
     * line Chart Init
     */
    function lineChartInit(data) {
        var option = {
               title: {
                   text: I18n.job_dashboard_date_report
               },
               tooltip : {
                   trigger: 'axis',
                   axisPointer: {
                       type: 'cross',
                       label: {
                           backgroundColor: '#6a7985'
                       }
                   }
               },
               legend: {
                   data:[I18n.joblog_status_suc, I18n.joblog_status_fail, I18n.joblog_status_running]
               },
               toolbox: {
                   feature: {
                       /*saveAsImage: {}*/
                   }
               },
               grid: {
                   left: '3%',
                   right: '4%',
                   bottom: '3%',
                   containLabel: true
               },
               xAxis : [
                   {
                       type : 'category',
                       boundaryGap : false,
                       data : data.content.triggerDayList
                   }
               ],
               yAxis : [
                   {
                       type : 'value'
                   }
               ],
               series : [
                   {
                       name:I18n.joblog_status_suc,
                       type:'line',
                       stack: 'Total',
                       areaStyle: {normal: {}},
                       data: data.content.triggerDayCountSucList
                   },
                   {
                       name:I18n.joblog_status_fail,
                       type:'line',
                       stack: 'Total',
                       label: {
                           normal: {
                               show: true,
                               position: 'top'
                           }
                       },
                       areaStyle: {normal: {}},
                       data: data.content.triggerDayCountFailList
                   },
                   {
                       name:I18n.joblog_status_running,
                       type:'line',
                       stack: 'Total',
                       areaStyle: {normal: {}},
                       data: data.content.triggerDayCountRunningList
                   }
               ],
                color:['#00A65A', '#c23632', '#F39C12']
        };

        var lineChart = echarts.init(document.getElementById('lineChart'));
        lineChart.setOption(option);
    }

    /**
     * pie Chart Init
     */
    function pieChartInit(data) {
        var option = {
            title : {
                text: I18n.job_dashboard_rate_report ,
                /*subtext: 'subtext',*/
                x:'center'
            },
            tooltip : {
                trigger: 'item',
                formatter: "{b} : {c} ({d}%)"
            },
            legend: {
                orient: 'vertical',
                left: 'left',
                data: [I18n.joblog_status_suc, I18n.joblog_status_fail, I18n.joblog_status_running ]
            },
            series : [
                {
                    //name: '分布比例',
                    type: 'pie',
                    radius : '55%',
                    center: ['50%', '60%'],
                    data:[
                        {
                            name:I18n.joblog_status_suc,
                            value:data.content.triggerCountSucTotal
                        },
                        {
                            name:I18n.joblog_status_fail,
                            value:data.content.triggerCountFailTotal
                        },
                        {
                            name:I18n.joblog_status_running,
                            value:data.content.triggerCountRunningTotal
                        }
                    ],
                    itemStyle: {
                        emphasis: {
                            shadowBlur: 10,
                            shadowOffsetX: 0,
                            shadowColor: 'rgba(0, 0, 0, 0.5)'
                        }
                    }
                }
            ],
            color:['#00A65A', '#c23632', '#F39C12']
        };
        var pieChart = echarts.init(document.getElementById('pieChart'));
        pieChart.setOption(option);
    }

});
