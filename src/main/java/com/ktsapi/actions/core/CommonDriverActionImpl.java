/**
 * 
 */
package com.ktsapi.actions.core;

import static com.ktsapi.actions.core.ActionsLogger.logAction;
import static com.ktsapi.actions.core.ActionsLogger.systemLogsInfo;

import com.ktsapi.actions.CommonDriverAction;
import com.ktsapi.actions.log.ActionLog;
import com.ktsapi.contexts.TestConfigurationDefaults;
import com.ktsapi.core.TestConfig;
import com.ktsapi.enums.KTestActions;
/**
 * [...]  
 *
 * @author  Bathiya L.
 * @version 1.0
 */
public class CommonDriverActionImpl implements CommonDriverAction {

	@Override
	public void pause(int timeOutInSeconds){
		String logMessage = "for " + timeOutInSeconds + " seconds";
    	try {    		
			Thread.sleep(timeOutInSeconds*1000);// convert seconds to millis
			logAction(ActionLog.actionLogWithDirectMesage(KTestActions.Pause,logMessage,null));	
		} catch (InterruptedException e) {
			logAction(ActionLog.actionLogWithDirectMesage(KTestActions.Pause,logMessage,e));
			e.printStackTrace();
		}
	}

	@Override
	public void print(String logMessage) {
		logAction(ActionLog.actionLogWithDirectMesage(KTestActions.Print,logMessage,null));		
	}

	@Override
	public String baseUrl() {
		
		String baseUrlInCache = TestConfig.getTestConfiguration().getBaseUrl();
		if(baseUrlInCache.equals(TestConfigurationDefaults.DEFAULT_BASE_URL)){
			systemLogsInfo("Base URL is same as system default url, it may have not overrode");
		}
		String logMessage = "Set as : " + baseUrlInCache;
//		logAction(ActionLog.actionLogWithDirectMesage(ABotActions.BaseUrl,logMessage,null));	
		return baseUrlInCache;
	}
}
