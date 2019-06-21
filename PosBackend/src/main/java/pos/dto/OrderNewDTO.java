package pos.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

public class OrderNewDTO {

    private int orderId;
    private String customerId;
    private String customerName;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date orderDate;
    private List<OrderDetailDTO> detailDTOList;

    public OrderNewDTO() {
    }

    public OrderNewDTO(int orderId, String customerId, String customerName, Date orderDate, List<OrderDetailDTO> detailDTOList) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.customerName = customerName;
        this.orderDate = orderDate;
        this.detailDTOList = detailDTOList;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public List<OrderDetailDTO> getDetailDTOList() {
        return detailDTOList;
    }
    public void setDetailDTOList(List<OrderDetailDTO> detailDTOList) {
        this.detailDTOList = detailDTOList;
    }

    @Override
    public String toString() {
        return "OrderNewDTO{" +
                "orderId=" + orderId +
                ", customerId='" + customerId + '\'' +
                ", customerName='" + customerName + '\'' +
                ", orderDate=" + orderDate +
                '}';
    }
}
