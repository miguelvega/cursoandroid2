package org.mvp.labs.android.services.aidl;

import org.mvp.labs.android.services.aidl.IRemoteMessageService;
import android.os.RemoteException;

public class TimeMessageService extends IRemoteMessageService.Stub {

	private final AIDLMessageService service;
	
	public TimeMessageService(AIDLMessageService service) {
		this.service = service;
	}
	
	@Override
	public String getMessage() throws RemoteException {
		return service.getStringForRemoteService();
	}

}
