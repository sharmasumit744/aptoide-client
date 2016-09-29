package com.aptoide.amethyst.utils;

import android.content.Context;
import android.content.Intent;
import com.aptoide.amethyst.Aptoide;

/**
 * Created by diogoloureiro on 27/09/16.
 */

public class IndusAnalytics {

  private static final String ACTION = "com.mofirst.playstore.action.REPORT_EVENT";
  private static final String INDUS_DEFAULT_STORE = "indus";
  private static Context context;

  //EXTRAS
  //private static final String INIT_EXTRA = "com.mofirst.playstore.extra.";
  private static final String EVENT_TITLE = "event_title";                            //String
  private static final String DOWNLOAD_SIZE = "download_size";                        //
  private static final String INSTALL_TYPE = "install_type";                          //String
  private static final String PRICE = "price";                                        //String
  // TODO: 29/09/16 o appid na tab updates é sempre 0
  private static final String ITEM_ID = "item_id";                                    //String
  private static final String PACKAGE_NAME = "package_name";                          //String
  private static final String SUCCESS = "success";                                    //boolean
  private static final String VERSION_CODE = "version_code";                          //Long
  private static final String REASON = "reason";                                      //String
  private static final String DOWNLOAD_TIME = "download_time";                        //Long
  private static final String DOWNLOAD_PERCENT = "download_percent";                  //Double
  private static final String BEFORE_DOWNLOAD_START = "before_download_start";        //boolean
  private static final String APK_PATH = "apk_path";                                  //they haven't described it yet

  /**
   *Intent called when user clicks the Download/Update/Downgrade button
   * @param download_size size of the downloaded file
   * @param install_type type of instalation (download, update, downgrade)
   * @param price price of the app
   * @param item_id app id
   * @param package_name name of the app package
   * @param context called context
   */
  public static void installClickedIntent(Long download_size, String install_type, String price,
      Long item_id, String package_name, Context context){
    if (Aptoide.getConfiguration().getDefaultStore().contains(INDUS_DEFAULT_STORE)) {
      Intent indusIntent = new Intent(INDUS_DEFAULT_STORE);
      indusIntent.putExtra(EVENT_TITLE,"install_clicked");
      indusIntent.putExtra(DOWNLOAD_SIZE,download_size);
      indusIntent.putExtra(INSTALL_TYPE,install_type);
      if(price!=null)
        indusIntent.putExtra(PRICE, price);
      else
        indusIntent.putExtra(PRICE,"0");
      indusIntent.putExtra(ITEM_ID, item_id);
      indusIntent.putExtra(PACKAGE_NAME,package_name);
      context.sendBroadcast(indusIntent);
    }
  }

  /**
   *Intent called when the download starts
   * @param success true if the download starts with success
   * @param version_code version code app downloaded
   * @param item_id app id
   * @param package_name name of the app package
   * @param install_type type of instalation (download, update, downgrade)
   * @param context called context
   */
  public static void downloadStartIntent(boolean success, String version_code, Long item_id,
      String package_name, String install_type, Context context){
    if (Aptoide.getConfiguration().getDefaultStore().contains(INDUS_DEFAULT_STORE)) {
      Intent indusIntent = new Intent(ACTION);
      indusIntent.putExtra(EVENT_TITLE,"download_start");
      indusIntent.putExtra(SUCCESS,success);
      indusIntent.putExtra(VERSION_CODE,version_code);
      indusIntent.putExtra(ITEM_ID, item_id);
      indusIntent.putExtra(PACKAGE_NAME,package_name);
      indusIntent.putExtra(INSTALL_TYPE,install_type);
      context.sendBroadcast(indusIntent);
    }
  }

  /**
   *Intent called every event occurred on download progress
   * @param reason reason for the download report
   * @param success true if download is successful
   * @param download_time download current time
   * @param item_id app id
   * @param package_name name of the app package
   * @param install_type type of installation (download, update, downgrade)
   * @param context called context
   */
  public static void downloadReportIntent(String reason, boolean success, Long download_time,
      Long item_id, String package_name, String install_type, Context context){
    if (Aptoide.getConfiguration().getDefaultStore().contains(INDUS_DEFAULT_STORE)) {
      Intent indusIntent = new Intent(ACTION);
      indusIntent.putExtra(EVENT_TITLE,"download_report");
      indusIntent.putExtra(REASON,reason);
      indusIntent.putExtra(SUCCESS,success);
      indusIntent.putExtra(DOWNLOAD_TIME,download_time);
      indusIntent.putExtra(ITEM_ID, item_id);
      indusIntent.putExtra(PACKAGE_NAME,package_name);
      indusIntent.putExtra(INSTALL_TYPE,install_type);
      context.sendBroadcast(indusIntent);
    }
  }

  // TODO: 27/09/16 installation_report is handled by us?

  // TODO: 27/09/16 uninstallation_report is handled by us?

  /**
   *Intent called when the user cancels the progress
   * @param download_percent percent of the current download
   * @param before_download_start true if the the user canceled before the download starts
   * @param download_time download current time
   * @param version_code version code app downloaded
   * @param item_id app id
   * @param package_name name of the app package
   * @param install_type type of installation (download, update, downgrade)
   * @param context called context
   */
  public static void cancelClickedIntent(double download_percent, boolean before_download_start,
      Long download_time, String version_code, Long item_id, String package_name, String install_type, Context context){
    if (Aptoide.getConfiguration().getDefaultStore().contains(INDUS_DEFAULT_STORE)) {
      Intent indusIntent = new Intent(ACTION);
      indusIntent.putExtra(EVENT_TITLE,"cancel_clicked");
      indusIntent.putExtra(DOWNLOAD_PERCENT,download_percent);
      indusIntent.putExtra(BEFORE_DOWNLOAD_START,before_download_start);
      indusIntent.putExtra(DOWNLOAD_TIME,download_time);
      indusIntent.putExtra(VERSION_CODE,version_code);
      indusIntent.putExtra(ITEM_ID,item_id);
      indusIntent.putExtra(PACKAGE_NAME,package_name);
      indusIntent.putExtra(INSTALL_TYPE,install_type);
      context.sendBroadcast(indusIntent);
    }
  }

  /**
   *Intent called when the download is completed and the installation is about to begin
   * @param apk_path path for the downloaded apk
   * @param context called context
   */
  public static void apkPathIntent(String apk_path, Context context){
    if (Aptoide.getConfiguration().getDefaultStore().contains(INDUS_DEFAULT_STORE)) {
      Intent indusIntent = new Intent(ACTION);
      indusIntent.putExtra(EVENT_TITLE,"apk_path");
      indusIntent.putExtra(APK_PATH,apk_path);
      context.sendBroadcast(indusIntent);
    }
  }

  public static Context getContext() {
    return context;
  }

  public static void setContext(Context context) {
    IndusAnalytics.context = context;
  }
}