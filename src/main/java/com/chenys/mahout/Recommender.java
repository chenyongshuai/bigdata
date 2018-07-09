package com.chenys.mahout;
/** @author:  v_chenyongshuai@:
  * @date:  2018年7月7日 上午11:41:34 
  * @version：   1.0.0
  * @describe:    
  */
import java.io.File;
import java.util.List;

import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.EuclideanDistanceSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;

import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;

import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

public class Recommender {
   public static void main(String args[]){
      try{
         //Creating data model
         DataModel model  = new FileDataModel(new File("resources/input.csv")); //data

         
         /*UserSimilarity user = new PearsonCorrelationSimilarity(model);
         UserNeighborhood neighbor = new NearestNUserNeighborhood(2, user, model);
         GenericUserBasedRecommender r = new GenericUserBasedRecommender(model, neighbor, user);
         LongPrimitiveIterator iter = model.getUserIDs();
     
         while (iter.hasNext()) {
             long uid = iter.nextLong();
             List<RecommendedItem> list1 = r.recommend(uid, 3);
             
             System.out.printf("uid:%s", uid);
             for (RecommendedItem ritem : list1) {
                 
                 System.out.printf("(%s,%f)", ritem.getItemID(), ritem.getValue());
             }
             System.out.println();
         }*/
         
         ItemSimilarity user = new PearsonCorrelationSimilarity(model);
         GenericItemBasedRecommender r = new GenericItemBasedRecommender(model, user);
         LongPrimitiveIterator iter = model.getUserIDs();
     
         while (iter.hasNext()) {
             long uid = iter.nextLong();
             List<RecommendedItem> list1 = r.recommend(uid, 3);
             
             System.out.printf("uid:%s", uid);
             for (RecommendedItem ritem : list1) {
                 
                 System.out.printf("(%s,%f)", ritem.getItemID(), ritem.getValue());
             }
             System.out.println();
         }
      }catch(Exception e){
    	  
    	  System.out.println(e);
      }
      
   }
  }