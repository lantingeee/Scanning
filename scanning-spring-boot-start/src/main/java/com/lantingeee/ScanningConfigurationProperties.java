package com.lantingeee;


import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration properties for Scanning. <br/>
 * This class is used for binding the configuration values in "application.properties" or "application.yml"
 *
 * @author lantingeee
 * @since 0.0.1
 */

@ConfigurationProperties(prefix = ScanningConfigurationProperties.PREFIX)
public class ScanningConfigurationProperties {

    /**
     * Prefix of properties names.
     */
    public static final String PREFIX = "scanning";

    private boolean enable = true;

    /**
     * Returns if Scanning should be enabled within application.
     *
     * @return <code>true<code/> if Scanning should be enabled， otherwise <code>false<code/> . Default value is true
     */
    public boolean isEnable() {
        return enable;
    }

    /**
     * Sets whether Scanning should be enabled within application.
     * @param enable <code>true<code/> if Scanning should be enabled within application . otherwise <code>false<code/>
     */
    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}
