package com.joyintech.tams.common.base.entity;

public class ValInterfServerInfo extends ValInterfServerInfoKey {
    /**
     * 服务器端口号
     */
    private String serverport;

    /**
     * 用户名
     */
    private String username;

    /**
     * 服务器登录密码
     */
    private String password;

    /**
     * 服务器登录类型
     */
    private String servertype;

    /**
     * 远程路径
     */
    private String remotePath;

    /**
     * 本地路径
     */
    private String localPath;

    /**
     * 服务名称(数据库SID)
     */
    private String servername;

    /**
     * 取得 服务器端口号
     * @return 服务器端口号
     */
    public String getServerport() {
        return serverport;
    }

    /**
     * 设置 服务器端口号
     * @param serverport 服务器端口号
     */
    public void setServerport(String serverport) {
        this.serverport = serverport == null ? null : serverport.trim();
    }

    /**
     * 取得 用户名
     * @return 用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置 用户名
     * @param username 用户名
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * 取得 服务器登录密码
     * @return 服务器登录密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置 服务器登录密码
     * @param password 服务器登录密码
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 取得 服务器登录类型
     * @return 服务器登录类型
     */
    public String getServertype() {
        return servertype;
    }

    /**
     * 设置 服务器登录类型
     * @param servertype 服务器登录类型
     */
    public void setServertype(String servertype) {
        this.servertype = servertype == null ? null : servertype.trim();
    }

    /**
     * 取得 远程路径
     * @return 远程路径
     */
    public String getRemotePath() {
        return remotePath;
    }

    /**
     * 设置 远程路径
     * @param remotePath 远程路径
     */
    public void setRemotePath(String remotePath) {
        this.remotePath = remotePath == null ? null : remotePath.trim();
    }

    /**
     * 取得 本地路径
     * @return 本地路径
     */
    public String getLocalPath() {
        return localPath;
    }

    /**
     * 设置 本地路径
     * @param localPath 本地路径
     */
    public void setLocalPath(String localPath) {
        this.localPath = localPath == null ? null : localPath.trim();
    }

    /**
     * 取得 服务名称(数据库SID)
     * @return 服务名称(数据库SID)
     */
    public String getServername() {
        return servername;
    }

    /**
     * 设置 服务名称(数据库SID)
     * @param servername 服务名称(数据库SID)
     */
    public void setServername(String servername) {
        this.servername = servername == null ? null : servername.trim();
    }
}