package com.epam.tat21.crypto.api.apiutils;

import com.epam.tat21.crypto.api.model.NewsItem;

import java.util.Comparator;

public class NewsItemComparator implements Comparator<NewsItem> {

    /**
     * News on the page are printed by their published time.
     * As bigger the time is, as higher the news is. If the news have the same time,
     * their order depends on id: as less id is - as higher the news is. It allows
     * to avoid mismatching between yhe news page and the api response.
     */

    @Override
    public int compare(NewsItem n1, NewsItem n2) {
        int time1 = n1.getPublished_on();
        int time2 = n2.getPublished_on();
        int id1 = n1.getPublished_on();
        int id2 = n2.getPublished_on();
        if (time1 > time2) {
            return -1;
        } else if (time1 < time2) {
            return 1;
        } else if (id1 > id2) {
            return 1;
        } else if (id1 < id2) {
            return -1;
        }
        return 0;
    }
}
