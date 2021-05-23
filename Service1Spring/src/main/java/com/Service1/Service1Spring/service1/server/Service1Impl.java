package com.Service1.Service1Spring.service1.server;

import com.Service1.Service1Spring.common.Ship;
import com.Service1.Service1Spring.service1.ScheduleGenerator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Service1Impl implements Service1Interface{

    @Override
    public List<Ship> getSchedule(int amount) {
        return new ScheduleGenerator(amount).getSchedule();
    }
}
