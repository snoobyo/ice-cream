package com.ic.business.controller;

import org.apache.zookeeper.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

@RestController
@RequestMapping("/zk")
public class ZookeeperController {

    @GetMapping("/add")
    public void createNode() {

    }

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        CountDownLatch cdl = new CountDownLatch(1);
        ZooKeeper zk = new ZooKeeper("localhost:2181", 5000, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                    cdl.countDown();
            }
        });
        cdl.await();
        System.out.println("连接成功");

        // 创建节点
//        if (zk.exists("zk", false)) {
//            zk.create("/zk", "myzk".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
//        }
        // 查询指定节点
        byte[] data = zk.getData("/zk", false, zk.exists("/zk", false));
        System.out.println(new String(data));

        // 查询所有节点

        // 删除指定节点
    }
}
