package yerp.work.HR.HRA;

import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import yerp.common.annotation.CommonParam;
import yerp.common.service.CommonService;
import yerp.common.util.APIResponse;
import yerp.common.util.ParameterUtil;
import yerp.common.util.SQLMap;
import yerp.common.valid.Validator;

/**
 * <ul>
 * <li>Grid_template_Controller</li>
 * <li>설명 : 그리드 화면 컨트롤러</li>
 * <li>작성일 : 2022. 04. 12</li>
 * <li>작성자 : 정준석</li>
 * </ul>
 */
@RestController
@RequestMapping("/HR/HRA")
public class HRAA_BC_CEO_Manage {
	@Autowired
	private CommonService commonService;
	
	@GetMapping("/HRAA_BC_CEO_Select")
	public ResponseEntity<JSONObject> HRAA_BC_CEO_Select(@CommonParam Map<String, Object> parameter) {
		JSONArray sqlResult = new JSONArray();
		APIResponse response = new APIResponse();
		try {
			String sqlMap = "HR.HRA.HRAA_BC_CEO_Select";
			sqlResult = commonService.selectList(sqlMap, parameter);
			//System.out.println(sqlResult);
			response.setResponse(sqlResult);
		} catch (Exception e) {
			response.setResponseForError(e);
		}
		return response.getEntity();
	}	
	
	@PostMapping("/HRAA_BC_CEO_Save")
	public ResponseEntity<JSONObject> HRAA_BC_CEO_Save(@CommonParam @RequestBody Map<String, Object> parameter) {
		APIResponse response = new APIResponse();
		
		try {
			JSONArray body = ParameterUtil.getBody(parameter);
			
			Validator validator = new Validator();
			//validator.add(new Required(new String[]{"RoleID"}));
			
			JSONArray validResult = validator.run(body);
			if (validator.isPass()) {
				SQLMap sqlMap = new SQLMap();
				sqlMap.setNew("HR.HRA.HRAA_MP_CEO_Insert");
				sqlMap.setModify("HR.HRA.HRAA_MP_CEO_Update");
				sqlMap.setRemove("HR.HRA.HRAA_MP_CEO_Delete");
				
				response.setResponse(commonService.bodyProcess(parameter, sqlMap));
			} else {
				response.setResponseForValidation(validResult);
			}
		} catch (Exception e) {
			response.setResponseForError(e);
		}
		return response.getEntity();
	}
}
