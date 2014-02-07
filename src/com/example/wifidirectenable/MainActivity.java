package com.example.wifidirectenable;

import java.util.HashMap;
import java.util.Map;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.net.wifi.p2p.WifiP2pManager;
import android.net.wifi.p2p.WifiP2pManager.ActionListener;
import android.net.wifi.p2p.WifiP2pManager.Channel;
import android.net.wifi.p2p.nsd.WifiP2pDnsSdServiceInfo;
import android.net.wifi.p2p.nsd.WifiP2pDnsSdServiceRequest;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private WifiP2pManager mWifiP2pManager;
	private Channel mChannel;
	private IntentFilter mIntentFilter;
	private BroadcastReceiver mReceiver;
	String Instance_name="xxxxxx";
	String Instance_Type="yyy._tcp";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mWifiP2pManager=(WifiP2pManager)getSystemService(Context.WIFI_P2P_SERVICE);
		mWifiP2pManager = (WifiP2pManager) getSystemService(Context.WIFI_P2P_SERVICE);
		    mChannel = mWifiP2pManager.initialize(this, getMainLooper(), null);
		  //  mReceiver = new WiFiDirectReceiver(mManager, mChannel, this);

		    mIntentFilter = new IntentFilter();
		    mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);
		    mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);
		    mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);
		    mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);
		    
		    
		    enableWiFiP2p();
	}

	private void enableWiFiP2p() {
		// TODO Auto-generated method stub
		HashMap<String, String> record=new HashMap<String, String>();
		record.put("Available", "vivible");
		WifiP2pDnsSdServiceInfo serviceInfo=WifiP2pDnsSdServiceInfo.newInstance(Instance_name, Instance_Type, record);
		
		mWifiP2pManager.addLocalService(mChannel, serviceInfo, new ActionListener() {
			
			@Override
			public void onSuccess() {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "Service Added", Toast.LENGTH_SHORT).show();
				
			}
			
			@Override
			public void onFailure(int reason) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "Service Failed", Toast.LENGTH_SHORT).show();
			}
		});
		next();
		
	}

	private void next() {
		// TODO Auto-generated method stub
		WifiP2pDnsSdServiceRequest request=WifiP2pDnsSdServiceRequest.newInstance();
		mWifiP2pManager.addServiceRequest(mChannel, request, new ActionListener() {
			
			@Override
			public void onSuccess() {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "Service Request Added", Toast.LENGTH_SHORT).show();	
			}
			@Override
			public void onFailure(int reason) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "Service Request Failed", Toast.LENGTH_SHORT).show();	
			}
		});
		mWifiP2pManager.discoverServices(mChannel, new ActionListener() {
			
			@Override
			public void onSuccess() {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "Discovered", Toast.LENGTH_SHORT).show();	
			}	
			@Override
			public void onFailure(int reason) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "DiscoverFailed", Toast.LENGTH_SHORT).show();
			}
		});	
	}
}
