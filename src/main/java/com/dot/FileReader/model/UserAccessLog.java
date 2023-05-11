package com.dot.FileReader.model;

import javax.persistence.*;

/** Represents a UserAccessLog.
 * @author Asukwo Enenim
 */
@Entity
@Table(name = "USER_ACCESS_LOG")
public class UserAccessLog {

    /** Creates a UserAccessLog with the specified details.
     * @param date The request time.
     * @param ip The request ip.
     * @param request The request.
     * @param status The request status.
     * @param userAgent The request user agent.
     */
    public UserAccessLog(String date, String ip, String request, String status, String userAgent) {
        this.date =date;
        this.ip = ip;
        this.request = request;
        this.status = status;
        this.userAgent = userAgent;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "DATE")
    private String date;

    @Column(name = "IP")
    private String ip;

    @Column(name = "REQUEST")
    private String request;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "USER_AGENT")
    private String userAgent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }
}
