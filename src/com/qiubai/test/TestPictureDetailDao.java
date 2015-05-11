package com.qiubai.test;

import java.util.List;

import org.junit.Test;

import com.qiubai.dao.PictureDao;
import com.qiubai.dao.PictureDetailDao;
import com.qiubai.dao.impl.PictureDaoImpl;
import com.qiubai.dao.impl.PictureDetailDaoImpl;
import com.qiubai.entity.Picture;
import com.qiubai.entity.PictureDetail;
import com.qiubai.server.PictureDetailManager;

public class TestPictureDetailDao {

	@Test
	public void test() {
//		PictureDetail pictureDetail = new PictureDetail();
//		pictureDetail.setId(1);
//		pictureDetail.setPic_address("D:/测试");
//		pictureDetail.setPic_describe("测试");

		PictureDetailDao pictureDetailDao = new PictureDetailDaoImpl();
//		System.out.println(pictureDetailDao.addPictureDetail(pictureDetail));
		
		PictureDao pictureDao = new PictureDaoImpl();
		PictureDetailManager pictureDetailManager = new PictureDetailManager();
		List<Picture> pictures = pictureDao.getAllPicture();
		for(Picture picture:pictures){
			String pic_url = picture.getPic_extra();
			if(pic_url!=null){
				int id = picture.getId();
				List<PictureDetail> pictureDetails = pictureDetailManager.getPictureDetailByUrl(pic_url,id);
				
				for(PictureDetail pictureDetail:pictureDetails){
					boolean flag = pictureDetailDao.addPictureDetail(pictureDetail);
//					System.out.println(id+"-->"+flag);
				}
			}
		}
	}

}
