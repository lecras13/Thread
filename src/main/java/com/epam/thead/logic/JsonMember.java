package com.epam.thead.logic;

import com.epam.thead.entity.Member;

import java.util.List;

public interface JsonMember {
    List<Member> create(String filePath);
}
