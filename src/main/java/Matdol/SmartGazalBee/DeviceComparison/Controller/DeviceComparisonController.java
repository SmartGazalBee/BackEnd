package Matdol.SmartGazalBee.DeviceComparison.Controller;

import Matdol.SmartGazalBee.Common.BeeResponse;
import Matdol.SmartGazalBee.Common.ResponseBody;
import Matdol.SmartGazalBee.Common.Status;
import Matdol.SmartGazalBee.DeviceComparison.Service.CrwalService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class DeviceComparisonController {

    @Autowired
    private final CrwalService crwalService;
    @PostMapping("/crwal")
    public ResponseEntity<ResponseBody<Boolean>> crwal(){
        boolean crwaling = crwalService.crwaling();
        System.out.println(crwaling);
        return BeeResponse.toResponse(Status.RUN,crwaling);
    }
}
