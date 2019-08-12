package com.team11.dataanalytics.openfeign.client;

import com.team11.dataanalytics.data.Data;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(url = "http://127.0.0.1:5000", name = "Test")
@Component
public interface PythonClient {

    @RequestMapping(value = "/analysisData", method = RequestMethod.POST, consumes="application/json")
    String pythonAnalysisData(Data data);
    @RequestMapping(value = "/getDataBySymbol", method = RequestMethod.POST, consumes="application/json")
    String pythonGetDataBySymbol(String symbol);
}
