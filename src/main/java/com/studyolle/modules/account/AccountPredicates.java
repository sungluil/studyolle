package com.studyolle.modules.account;

import java.util.Set;

import com.querydsl.core.types.Predicate;
import com.studyolle.modules.tag.Tag;
import com.studyolle.modules.zone.Zone;


public class AccountPredicates {

    public static Predicate findByTagsAndZones(Set<Tag> tags, Set<Zone> zones) {
    	return QAccount.account.zones.any().in(zones).and(QAccount.account.tags.any().in(tags));
    }
}
