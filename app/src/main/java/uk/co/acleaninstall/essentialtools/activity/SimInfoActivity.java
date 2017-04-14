package uk.co.acleaninstall.essentialtools.activity;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;
import java.util.ArrayList;
import java.util.List;
import uk.co.acleaninstall.essentialtools.MyApp;
import uk.co.acleaninstall.essentialtools.R;
import uk.co.acleaninstall.essentialtools.adapter.SimRecyclerviewAdapter;
import uk.co.acleaninstall.essentialtools.listener.CustomItemClickListener;
import uk.co.acleaninstall.essentialtools.model.SimModel;
import uk.co.acleaninstall.essentialtools.util.MiscTools;

public class SimInfoActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.simInfoRecyclerView)
    RecyclerView mRecyclerView;

    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sim_info);
        ButterKnife.bind(this);

        setupToolbar();

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // Custom Item Click Listener taken from https://gist.github.com/riyazMuhammad/1c7b1f9fa3065aa5a46f
        mAdapter = new SimRecyclerviewAdapter(new SimInfoModelsList().getList(),
            new CustomItemClickListener() {
                @Override
                public void onItemClick(View v, int position) {
                    // Setup on clicks if required
                }
            });
        mRecyclerView.setAdapter(mAdapter);
    }


    @Override
    protected void onResume() {
        super.onResume();

        mRecyclerView.setAdapter(mAdapter);
    }


    private void setupToolbar() {

        // Setup our Toolbar as an ActionBar
        setSupportActionBar(mToolbar);

        // Check for our Action Bat / Toolbar
        if (getSupportActionBar() != null) {

            // Set the back button on it
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            // Set the top padding to bring the view down
            mToolbar.setPadding(0, MiscTools.getStatusBarHeight(), 0, 0);
        }
    }


    private class SimInfoModelsList extends ArrayList<SimModel> {

        List<SimModel> list;
        int state;


        SimInfoModelsList() {
            list = new ArrayList<>();
            state = MyApp.getTelephonyManager().getSimState();
        }


        @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
        ArrayList<SimModel> getList() {

            // Our list of sim info models to choose from
            SimModel cellInfoList = new SimModel("Call information",
                "The current state of the phone in call",
                getCallState(), getResources().getString(R.string.gmd_sim_card_alert));

            @SuppressLint("HardwareIds") SimModel imei = new SimModel("IMEI Number",
                "The phones IMEI number shown",
                MyApp.getTelephonyManager().getDeviceId(),
                getResources().getString(R.string.gmd_format_list_numbered));

            SimModel imeiSv = new SimModel("IMEI SV", "The phones IMEI SV number shown",
                MyApp.getTelephonyManager().getDeviceSoftwareVersion(),
                getResources().getString(R.string.gmd_sim_card_alert));

            SimModel line1Number = new SimModel("Telephone number",
                "The phones line 1 phone number shown",
                MyApp.getTelephonyManager().getLine1Number(),
                getResources().getString(R.string.gmd_sim_card_alert));

            SimModel networkCountryISo = new SimModel("Network country ISO",
                "The phones network country ISO shown",
                MyApp.getTelephonyManager().getNetworkCountryIso(),
                getResources().getString(R.string.gmd_flag));

            SimModel networkOperator = new SimModel("Network operator",
                "The phones network operator shown",
                MyApp.getTelephonyManager().getNetworkOperator(),
                getResources().getString(R.string.gmd_network_cell));

            SimModel networkOperatorName = new SimModel("Network operator name",
                "The phones network operator name shown",
                MyApp.getTelephonyManager().getNetworkOperatorName(),
                getResources().getString(R.string.gmd_network_cell));

            SimModel cellLocation = new SimModel("Cell location",
                "Returns the current location of the device" + "\n" + "\n" +
                    "Location Area Code | GSM Cell Id | Primary Scrambling Code",
                MyApp.getTelephonyManager().getCellLocation().toString(),
                getResources().getString(R.string.gmd_my_location));

            SimModel groupIdLevel1 = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) {
                groupIdLevel1 = new SimModel("Group id",
                    "Returns the Group Identifier Level1 for a GSM phone",
                    MyApp.getTelephonyManager().getGroupIdLevel1(),
                    getResources().getString(R.string.gmd_network_cell));
            }

            SimModel mmsUAProfUrl = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                mmsUAProfUrl = new SimModel("MMS user agent profile URL",
                    "Returns the MMS user agent profile URL",
                    MyApp.getTelephonyManager().getMmsUAProfUrl(),
                    getResources().getString(R.string.gmd_my_location));
            }

            SimModel mmsUerAgent = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                mmsUerAgent = new SimModel("MMS user agent", "Returns the MMS user agent",
                    MyApp.getTelephonyManager().getMmsUserAgent(),
                    getResources().getString(R.string.gmd_my_location));
            }

            SimModel phoneCount = new SimModel("Single/Dual sim",
                "If the phone is single sim or dual",
                getPhoneCount(), getResources().getString(R.string.gmd_network_cell));

            SimModel phoneType = new SimModel("Radio type", "The phones radio type",
                getPhoneType(), getResources().getString(R.string.gmd_network_cell));

            SimModel simCountryIso = new SimModel("Sim ISO country",
                "Returns the ISO country code equivalent for the SIM provider's country code",
                MyApp.getTelephonyManager().getSimCountryIso(),
                getResources().getString(R.string.gmd_sim_card));

            SimModel simState = new SimModel("Sim state",
                "Returns the state of the default SIM card",
                getSimState(), getResources().getString(R.string.gmd_network_cell));

            SimModel simOperator = null;
            if (state == TelephonyManager.SIM_STATE_READY) {
                simOperator = new SimModel("Sim operator",
                    "Returns the MCC+MNC (mobile country code + mobile network code) of the provider of the SIM. 5 or 6 decimal digits",
                    MyApp.getTelephonyManager().getSimOperator(),
                    getResources().getString(R.string.gmd_my_location));
            }

            SimModel simOperatorName = null;
            if (state == TelephonyManager.SIM_STATE_READY) {
                simOperatorName = new SimModel("Sim operator name",
                    "Returns the Service Provider Name (SPN)",
                    MyApp.getTelephonyManager().getSimOperatorName(),
                    getResources().getString(R.string.gmd_my_location));
            }

            @SuppressLint("HardwareIds") SimModel simSerial = new SimModel("Sim serial",
                "Returns the serial number of the default SIM card",
                MyApp.getTelephonyManager().getSimSerialNumber(),
                getResources().getString(R.string.gmd_network_cell));

            SimModel voiceMailAphaTag = new SimModel("Voicemail identifier",
                "Retrieves the alphabetic identifier associated with the voice mail number",
                MyApp.getTelephonyManager().getVoiceMailAlphaTag(),
                getResources().getString(R.string.gmd_network_cell));

            SimModel voiceMailNumber = null;
            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                voiceMailNumber = new SimModel("Voicemail number",
                    "Retrieves the number associated with the voice mail",
                    getVoiceMail(), getResources().getString(R.string.gmd_network_cell));
            }

            SimModel voiceNetworkType = null;
            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                voiceNetworkType = new SimModel("Voice network type",
                    "Returns the Network Type for voice",
                    getVoiceNetworkType(), getResources().getString(R.string.gmd_my_location));
            }

            SimModel hasCarrierPrivs = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP_MR1) {
                hasCarrierPrivs = new SimModel("Carrier privileges",
                    "Has the calling application been granted carrier privileges by the carrier",
                    String.valueOf(MyApp.getTelephonyManager().hasCarrierPrivileges()),
                    getResources().getString(R.string.gmd_network_cell));
            }

            SimModel hasIccCard = new SimModel("Icc card present", "true if a ICC card is present",
                String.valueOf(MyApp.getTelephonyManager().hasIccCard()),
                getResources().getString(R.string.gmd_network_cell));

            list.add(cellInfoList);
            list.add(imei);
            list.add(imeiSv);
            list.add(line1Number);
            list.add(networkCountryISo);
            list.add(networkOperator);
            list.add(networkOperatorName);
            list.add(cellLocation);
            list.add(groupIdLevel1);
            list.add(mmsUAProfUrl);
            list.add(mmsUerAgent);
            list.add(phoneCount);
            list.add(phoneType);
            list.add(simCountryIso);
            list.add(simState);

            // Only add these if our sim card is in a ready state
            if (state == TelephonyManager.SIM_STATE_READY) {
                list.add(simOperator);
                list.add(simOperatorName);
            }

            list.add(simSerial);
            list.add(voiceMailAphaTag);
            list.add(voiceMailNumber);
            list.add(voiceNetworkType);
            list.add(hasCarrierPrivs);
            list.add(hasIccCard);

            return (ArrayList<SimModel>) list;
        }

    }


    private String getVoiceMail() {

        String number = MyApp.getTelephonyManager().getVoiceMailNumber();

        if (number == null) {
            number = "null";
        }

        return number;
    }


    // different call states to return
    @NonNull
    private String getCallState() {

        int state = MyApp.getTelephonyManager().getCallState();

        switch (state) {

            case 0:
                return "No call active / idle";
            case 1:
                return "1 call active / No new call";
            case 2:
                return "New call ringing / waiting";
            default:
                return "Unknown call state";

        }
    }


    // Different phone count states to return
    @NonNull
    private String getPhoneCount() {

        String unsupported = "Device API too low requires API 23 M";

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            int count = MyApp.getTelephonyManager().getPhoneCount();

            switch (count) {

                case 0:
                    return "Sim cannot make a call / text / data , sim not supported";
                case 1:
                    return "Single sim";
                case 2:
                    return "Dual sim";
                default:
                    return "N/A";
            }
        }

        return unsupported;

    }


    // Different phone type states to return
    @NonNull
    private String getPhoneType() {

        int type = MyApp.getTelephonyManager().getPhoneType();

        switch (type) {

            case 0:
                return "No phone radio";
            case 1:
                return "GSM";
            case 2:
                return "CDMA";
            case 3:
                return "SIP";
            default:
                return "Unknown radio!";
        }

    }


    // Different phone type states to return
    @NonNull
    private String getSimState() {

        int state = MyApp.getTelephonyManager().getSimState();

        switch (state) {

            case 0:
                return "Unknown";
            case 1:
                return "Absent";
            case 2:
                return "Pin required";
            case 3:
                return "Puk required";
            case 4:
                return "Network locked";
            case 5:
                return "Ready";
            default:
                return "Unknown";
        }

    }


    // Different phone type states to return
    @NonNull
    private String getVoiceNetworkType() {

        String unsupported = "Device API too low requires API 24 N";

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            int voiceTyoe = MyApp.getTelephonyManager().getVoiceNetworkType();

            switch (voiceTyoe) {

                case TelephonyManager.NETWORK_TYPE_UNKNOWN:
                    return "Unknown";
                case TelephonyManager.NETWORK_TYPE_GPRS:
                    return "GPRS";
                case TelephonyManager.NETWORK_TYPE_EDGE:
                    return "EDGE";
                case TelephonyManager.NETWORK_TYPE_UMTS:
                    return "UMTS";
                case TelephonyManager.NETWORK_TYPE_CDMA:
                    return "CDMA";
                case TelephonyManager.NETWORK_TYPE_EVDO_0:
                    return "EVDO Rev 0";
                case TelephonyManager.NETWORK_TYPE_EVDO_A:
                    return "EVDO Rev A";
                case TelephonyManager.NETWORK_TYPE_1xRTT:
                    return "1xRTT";
                case TelephonyManager.NETWORK_TYPE_HSDPA:
                    return "HSDPA";
                case TelephonyManager.NETWORK_TYPE_HSUPA:
                    return "HSUPA";
                case TelephonyManager.NETWORK_TYPE_HSPA:
                    return "HSPA";
                case TelephonyManager.NETWORK_TYPE_IDEN:
                    return "IDEN";
                case TelephonyManager.NETWORK_TYPE_EVDO_B:
                    return "EVDO Rev B";
                case TelephonyManager.NETWORK_TYPE_LTE:
                    return "LTE";
                case TelephonyManager.NETWORK_TYPE_EHRPD:
                    return "EHPRD";
                case TelephonyManager.NETWORK_TYPE_HSPAP:
                    return "HSPAP";
                case TelephonyManager.NETWORK_TYPE_GSM:
                    return "GSM";
                case TelephonyManager.NETWORK_TYPE_TD_SCDMA:
                    return "TD_SCDMA";
                case TelephonyManager.NETWORK_TYPE_IWLAN:
                    return "IWLAN";
                default:
                    return "Unknown";
            }
        }

        return unsupported;

    }
}
