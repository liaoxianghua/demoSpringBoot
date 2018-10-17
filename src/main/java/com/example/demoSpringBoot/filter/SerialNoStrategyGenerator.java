package com.example.demoSpringBoot.filter;

import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class SerialNoStrategyGenerator implements StrategyGenerator {
    private static SerialNoStrategyGenerator SerialNoStrategyGenerator = new SerialNoStrategyGenerator();

    public static SerialNoStrategyGenerator getInstance() {
        return SerialNoStrategyGenerator;
    }

    //弃用，线程不安全，改用joda-time
    // private static final SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
    private static final DateTimeFormatter dt = DateTimeFormat.forPattern("yyyyMMddHHmmss");
    private String time;
    private static DateTime dateTime = new DateTime();
    private AtomicInteger counter = new AtomicInteger(0);
    private static final String DEFAULT_MAC = "000000";
    private static String mac = DEFAULT_MAC;


    static {
        try {
            InetAddress ia = InetAddress.getLocalHost();
            byte[] macAddr = NetworkInterface.getByInetAddress(ia).getHardwareAddress();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < macAddr.length; i++) {
                String s = Integer.toHexString(macAddr[i] & 0xFF);
                sb.append(s.length() == 1 ? 0 + s : s);//凑两位数
            }
            mac = sb.substring(6, 12).toUpperCase(); //转大写
        } catch (Exception e) {
            log.error("can not get the mac address");
        }
    }

    public String generate() {
        return new StringBuilder().append(getDate()).append(mac).append(getCounter()).toString();
    }

    private static String getDate() {
        return dt.print(dateTime.getMillis()).substring(2, 14);
    }

    private String getCounter() {

        if (getDate().equals(time)) {
            counter.getAndIncrement();
        } else {
            time = getDate();
            counter = new AtomicInteger(0);
        }

        return String.format("%06d", counter.get());
    }
}


