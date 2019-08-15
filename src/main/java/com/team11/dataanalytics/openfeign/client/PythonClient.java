package com.team11.dataanalytics.openfeign.client;

import com.team11.dataanalytics.data.Data;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;
// hotpot 192.168.43.8
// home 192.168.0.102
@FeignClient(url = "http://192.168.43.8:5000", name = "PythonServer")
@Component
public interface PythonClient {

    @RequestMapping(value = "/getDataByDict", method = RequestMethod.POST, consumes="application/json")
    String pythonGetDataByDict(Map map);

    @RequestMapping(value = "/getOriginData", method = RequestMethod.POST, consumes="application/json")
    String pythonGetOriginData(Map map);

    @RequestMapping(value = "/getOtherData", method = RequestMethod.POST, consumes="application/json")
    String pythonGetOtherData(Map map);

    @RequestMapping(value = "/getYahooData", method = RequestMethod.POST, consumes="application/json")
    String pythonGetYahooData(Map map);

    @RequestMapping(value = "/getChartHtml", method = RequestMethod.POST, consumes="application/json")
    String pythonGetChartHtml(Map map);
}
