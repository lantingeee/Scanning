package com.lantingeee.scanning;


/**
 * Created by lantingeee on 09/12/2017.
 */
public class ScanningImpl implements Scanning{

    @Override
    public void active() {
        new MappingURLCollection().getAllMethodStatus();
    }
}
