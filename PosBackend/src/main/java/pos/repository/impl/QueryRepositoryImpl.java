package pos.repository.impl;

import org.springframework.stereotype.Repository;
import pos.entity.CustomEntity;
import pos.entity.Item;
import pos.entity.OrderDetail;
import pos.repository.QueryRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class QueryRepositoryImpl implements QueryRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<CustomEntity> getOrdersTotal() {

        List<Object[]> list = em.createNativeQuery("SELECT id, SUM(qty * unitPrice) AS Total FROM `Order` INNER JOIN\n" +
                "  OrderDetail OD on `Order`.id = OD.orderId GROUP BY id").getResultList();

        List<CustomEntity> al = new ArrayList<>();

        for (Object[] objects : list) {
            al.add(new CustomEntity((Integer) objects[0], (Double) objects[1]));
        }

        return al;
    }

    @Override
    public List<CustomEntity> getAllOrders() {

        List<Object[]>  list = em.createNativeQuery("SELECT o.id, o.date, o.customerId,c.name FROM `order` o INNER JOIN customer c on o.customerId = c.id").getResultList();
        List<CustomEntity> al = new ArrayList<>();

        for (Object[] objects : list) {

            List<OrderDetail> orderDetails = new ArrayList<>();

            try {
                int oid = (Integer) objects[0];
                String odate = objects[1].toString();
                String cid = objects[2].toString();
                DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date date = formatter.parse(odate);
                String cname = objects[3].toString();

                Query nativeQuery = em.createNativeQuery("SELECT orderId, itemCode, qty, unitPrice FROM orderdetail WHERE orderId=?");
                nativeQuery.setParameter(1, oid);
                List<Object[]> resultList = nativeQuery.getResultList();
                List<OrderDetail> orderDetails1 = new ArrayList<>();

                for (Object[] obj : resultList) {
                    orderDetails1.add(new OrderDetail(Integer.parseInt(obj[0].toString()),obj[1].toString(),Integer.parseInt(obj[2].toString()),Double.parseDouble(obj[3].toString())));
                }

                al.add(new CustomEntity(oid,cid,cname,date,orderDetails1 ));

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return al;
    }

    @Override
    public List<Item> getOrder(int id) {

        Query nativeQuery = em.createNativeQuery("SELECT od.itemCode, it.description, od.unitPrice, od.qty FROM orderdetail od INNER JOIN item it ON od.itemCode = it.code WHERE orderId=?");
        nativeQuery.setParameter(1,id);
        List<Object[]> resultList = nativeQuery.getResultList();
        List<Item> customEntities = new ArrayList<>();

        for (Object[] objects : resultList) {
            customEntities.add(new Item(objects[0].toString(),objects[1].toString(),Double.parseDouble(objects[2].toString()),Integer.parseInt(objects[3].toString())));
        }

        return customEntities;
    }
}
