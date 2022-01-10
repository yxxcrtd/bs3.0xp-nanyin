package com.joyintech.tams.exchange.common.constant;

public enum RepoOperation {
    ESTABLISH("T00", "首期"),
    RENEW("T56", "到期续做"),
    TERMINATE("T95", "到期购回"),
    PRECEDE("T96", "提前购回"),
    CHANGE("T94", "中途换券"),
    RELEASE("T93", "解除质押"),
    ;
    String code;
    String name;

    RepoOperation(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static RepoOperation getRepoOperation(String code) {
        for (RepoOperation repoOperation : values()) {
            if (repoOperation.code.equals(code)) {
                return repoOperation;
            }
        }
        return null;
    }
}
