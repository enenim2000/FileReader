package com.dot.FileReader.model;

import javax.persistence.*;

/** Represents a BlockedIpTable.
 * @author Asukwo Enenim
 */
@Entity
@Table(name = "BLOCKED_IP_TABLE")
public class BlockedIpTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "IP")
    private String ip;

    @Column(name = "REQUEST_NUMBER")
    private String requestNumber;

    @Column(name = "COMMENT")
    private String comment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getRequestNumber() {
        return requestNumber;
    }

    public void setRequestNumber(String requestNumber) {
        this.requestNumber = requestNumber;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "BlockedIpTable{"+
                "ip='" + ip + '\'' +
                ", requestNumber='" + requestNumber + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
