/* The package where the aidl file is located */
package org.mvp.labs.android.services.aidl;

/* The name of the remote service */
interface IRemoteMessageService {

	/* A simple Method which will return a message string */
	String getMessage();

}