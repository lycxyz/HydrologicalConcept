package com.opengms.HydrologicalConcept.service;

import com.opengms.HydrologicalConcept.dao.GeoIconDao;
import com.opengms.HydrologicalConcept.dao.Impl.IconDao;
import com.opengms.HydrologicalConcept.entity.GeoIcon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Description: HydrologicalConcept
 * <p>
 * Created by LYC on 2019/12/24 14:32
 */
@Service
public class GeoIconService {

    private static final String pathUrl = "upload\\geoIcon\\";

    @Autowired
    IconDao iconDao;
    @Autowired
    GeoIconDao geoIconDao;

    public String uploadGeoIcons(MultipartFile mfile, GeoIcon geoIcon) {
        iconDao.saveIcon2File(mfile);
        geoIcon.setPathUrl(pathUrl + geoIcon.getPathUrl());
        geoIconDao.insert(geoIcon);
        return "ok";
    }

    public GeoIcon getGeoIconByGeoId(String geoId){
        GeoIcon icon =geoIconDao.findGeoIconByGeoId(geoId);
        return icon;
    }

    public List<GeoIcon> getAllGeoIcons(){
        return geoIconDao.findAll();
    }
}
