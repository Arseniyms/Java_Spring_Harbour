package com.Service1.Service1Spring.service1.server;

import com.Service1.Service1Spring.common.Ship;
import java.util.List;

public interface Service1Interface {
    List<Ship> getSchedule(int amount);
}
