package com.metacrew.pr2s.service.storageservice;

import lombok.Getter;

@Getter
public enum FilePath {
    upload_test("테스트")
    ,upload_client("사용자")
    ,upload_board("게시판")
    ,upload_admin("관리자");

    final String name;
    FilePath(String name){
        this.name = name;
    }
}
