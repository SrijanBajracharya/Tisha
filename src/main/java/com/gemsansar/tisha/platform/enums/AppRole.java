package com.gemsansar.tisha.platform.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public enum AppRole {
	ADMIN,
	USER,
	SUPPLIER;

	public static List<AppRole> getNonUserRole(){
		return List.of(ADMIN, SUPPLIER);
	}
}