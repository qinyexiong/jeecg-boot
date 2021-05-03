package org.jeecg.modules.demo.tel.entity;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;
import java.io.IOException;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.core.JsonProcessingException;

public class Converter {
    // Serialize/deserialize helpers

    public static Redata4Rque fromJsonString(String json) throws IOException {
        return getObjectReader().readValue(json);
    }

    public static String toJsonString(Redata4Rque obj) throws JsonProcessingException {
        return getObjectWriter().writeValueAsString(obj);
    }

    private static ObjectReader reader;
    private static ObjectWriter writer;

    private static void instantiateMapper() {
        ObjectMapper mapper = new ObjectMapper();
        reader = mapper.readerFor(Redata4Rque.class);
        writer = mapper.writerFor(Redata4Rque.class);
    }

    private static ObjectReader getObjectReader() {
        if (reader == null) instantiateMapper();
        return reader;
    }

    private static ObjectWriter getObjectWriter() {
        if (writer == null) instantiateMapper();
        return writer;
    }


// Redata4Rque.java


    public static class Redata4Rque {

        private String code;
        private boolean success;
        private String message;
        private Result result;

        @JsonProperty("code")
        public String getCode() {
            return code;
        }

        @JsonProperty("code")
        public void setCode(String value) {
            this.code = value;
        }

        @JsonProperty("success")
        public boolean getSuccess() {
            return success;
        }

        @JsonProperty("success")
        public void setSuccess(boolean value) {
            this.success = value;
        }

        @JsonProperty("message")
        public String getMessage() {
            return message;
        }

        @JsonProperty("message")
        public void setMessage(String value) {
            this.message = value;
        }

        @JsonProperty("result")
        public Result getResult() {
            return result;
        }

        @JsonProperty("result")
        public void setResult(Result value) {
            this.result = value;
        }


// Result.java


        public static class Result {
            private long current;
            private long pages;
            private long size;
            private long total;
            private Record[] records;

            @JsonProperty("current")
            public long getCurrent() {
                return current;
            }

            @JsonProperty("current")
            public void setCurrent(long value) {
                this.current = value;
            }

            @JsonProperty("pages")
            public long getPages() {
                return pages;
            }

            @JsonProperty("pages")
            public void setPages(long value) {
                this.pages = value;
            }

            @JsonProperty("size")
            public long getSize() {
                return size;
            }

            @JsonProperty("size")
            public void setSize(long value) {
                this.size = value;
            }

            @JsonProperty("total")
            public long getTotal() {
                return total;
            }

            @JsonProperty("total")
            public void setTotal(long value) {
                this.total = value;
            }

            @JsonProperty("records")
            public Record[] getRecords() {
                return records;
            }

            @JsonProperty("records")
            public void setRecords(Record[] value) {
                this.records = value;
            }
        }

// Record.java

        public static class Record {
            private Follow follow;
            private Client client;
            private User user;
            private LoanCase[] loanCase;
            private boolean idMask;
            private boolean phoneMask;

            @JsonProperty("follow")
            public Follow getFollow() {
                return follow;
            }

            @JsonProperty("follow")
            public void setFollow(Follow value) {
                this.follow = value;
            }

            @JsonProperty("client")
            public Client getClient() {
                return client;
            }

            @JsonProperty("client")
            public void setClient(Client value) {
                this.client = value;
            }

            @JsonProperty("user")
            public User getUser() {
                return user;
            }

            @JsonProperty("user")
            public void setUser(User value) {
                this.user = value;
            }

            @JsonProperty("loanCase")
            public LoanCase[] getLoanCase() {
                return loanCase;
            }

            @JsonProperty("loanCase")
            public void setLoanCase(LoanCase[] value) {
                this.loanCase = value;
            }

            @JsonProperty("idMask")
            public boolean getIDMask() {
                return idMask;
            }

            @JsonProperty("idMask")
            public void setIDMask(boolean value) {
                this.idMask = value;
            }

            @JsonProperty("phoneMask")
            public boolean getPhoneMask() {
                return phoneMask;
            }

            @JsonProperty("phoneMask")
            public void setPhoneMask(boolean value) {
                this.phoneMask = value;
            }
        }

// Client.java


        public static class Client {
            private String clientID;
            private String realName;
            private long age;
            private String gender;
            private String mobile;
            private String oriMobile;
            private String idCard;
            private IsRegister isRegister;

            @JsonProperty("clientId")
            public String getClientID() {
                return clientID;
            }

            @JsonProperty("clientId")
            public void setClientID(String value) {
                this.clientID = value;
            }

            @JsonProperty("realName")
            public String getRealName() {
                return realName;
            }

            @JsonProperty("realName")
            public void setRealName(String value) {
                this.realName = value;
            }

            @JsonProperty("age")
            public long getAge() {
                return age;
            }

            @JsonProperty("age")
            public void setAge(long value) {
                this.age = value;
            }

            @JsonProperty("gender")
            public String getGender() {
                return gender;
            }

            @JsonProperty("gender")
            public void setGender(String value) {
                this.gender = value;
            }

            @JsonProperty("mobile")
            public String getMobile() {
                return mobile;
            }

            @JsonProperty("mobile")
            public void setMobile(String value) {
                this.mobile = value;
            }

            @JsonProperty("oriMobile")
            public String getOriMobile() {
                return oriMobile;
            }

            @JsonProperty("oriMobile")
            public void setOriMobile(String value) {
                this.oriMobile = value;
            }

            @JsonProperty("idCard")
            public String getIDCard() {
                return idCard;
            }

            @JsonProperty("idCard")
            public void setIDCard(String value) {
                this.idCard = value;
            }

            @JsonProperty("isRegister")
            public IsRegister getIsRegister() {
                return isRegister;
            }

            @JsonProperty("isRegister")
            public void setIsRegister(IsRegister value) {
                this.isRegister = value;
            }
        }

// IsRegister.java


        public static class IsRegister {
            private String code;
            private String message;
            private String message2;

            @JsonProperty("code")
            public String getCode() {
                return code;
            }

            @JsonProperty("code")
            public void setCode(String value) {
                this.code = value;
            }

            @JsonProperty("message")
            public String getMessage() {
                return message;
            }

            @JsonProperty("message")
            public void setMessage(String value) {
                this.message = value;
            }

            @JsonProperty("message2")
            public String getMessage2() {
                return message2;
            }

            @JsonProperty("message2")
            public void setMessage2(String value) {
                this.message2 = value;
            }
        }

// Follow.java


        public static class Follow {
            private String id;
            private String customerID;
            private String userID;
            private String clientID;
            private long earliestRepaymentDay;
            private String bizLevel;
            private String state;
            private long created;
            private long updated;
            private long aiSupport;
            private long lastFollowDay;
            private String channelFrom;
            private RelateReachable selfReachable;
            private RelateReachable relateReachable;
            private Object[] colorTagEnums;
            private long totalCaseAmount;
            private RelateReachable[] overdueStateList;

            @JsonProperty("id")
            public String getID() {
                return id;
            }

            @JsonProperty("id")
            public void setID(String value) {
                this.id = value;
            }

            @JsonProperty("customerId")
            public String getCustomerID() {
                return customerID;
            }

            @JsonProperty("customerId")
            public void setCustomerID(String value) {
                this.customerID = value;
            }

            @JsonProperty("userId")
            public String getUserID() {
                return userID;
            }

            @JsonProperty("userId")
            public void setUserID(String value) {
                this.userID = value;
            }

            @JsonProperty("clientId")
            public String getClientID() {
                return clientID;
            }

            @JsonProperty("clientId")
            public void setClientID(String value) {
                this.clientID = value;
            }

            @JsonProperty("earliestRepaymentDay")
            public long getEarliestRepaymentDay() {
                return earliestRepaymentDay;
            }

            @JsonProperty("earliestRepaymentDay")
            public void setEarliestRepaymentDay(long value) {
                this.earliestRepaymentDay = value;
            }

            @JsonProperty("bizLevel")
            public String getBizLevel() {
                return bizLevel;
            }

            @JsonProperty("bizLevel")
            public void setBizLevel(String value) {
                this.bizLevel = value;
            }

            @JsonProperty("state")
            public String getState() {
                return state;
            }

            @JsonProperty("state")
            public void setState(String value) {
                this.state = value;
            }

            @JsonProperty("created")
            public long getCreated() {
                return created;
            }

            @JsonProperty("created")
            public void setCreated(long value) {
                this.created = value;
            }

            @JsonProperty("updated")
            public long getUpdated() {
                return updated;
            }

            @JsonProperty("updated")
            public void setUpdated(long value) {
                this.updated = value;
            }

            @JsonProperty("aiSupport")
            public long getAISupport() {
                return aiSupport;
            }

            @JsonProperty("aiSupport")
            public void setAISupport(long value) {
                this.aiSupport = value;
            }

            @JsonProperty("lastFollowDay")
            public long getLastFollowDay() {
                return lastFollowDay;
            }

            @JsonProperty("lastFollowDay")
            public void setLastFollowDay(long value) {
                this.lastFollowDay = value;
            }

            @JsonProperty("channelFrom")
            public String getChannelFrom() {
                return channelFrom;
            }

            @JsonProperty("channelFrom")
            public void setChannelFrom(String value) {
                this.channelFrom = value;
            }

            @JsonProperty("selfReachable")
            public RelateReachable getSelfReachable() {
                return selfReachable;
            }

            @JsonProperty("selfReachable")
            public void setSelfReachable(RelateReachable value) {
                this.selfReachable = value;
            }

            @JsonProperty("relateReachable")
            public RelateReachable getRelateReachable() {
                return relateReachable;
            }

            @JsonProperty("relateReachable")
            public void setRelateReachable(RelateReachable value) {
                this.relateReachable = value;
            }

            @JsonProperty("colorTagEnums")
            public Object[] getColorTagEnums() {
                return colorTagEnums;
            }

            @JsonProperty("colorTagEnums")
            public void setColorTagEnums(Object[] value) {
                this.colorTagEnums = value;
            }

            @JsonProperty("totalCaseAmount")
            public long getTotalCaseAmount() {
                return totalCaseAmount;
            }

            @JsonProperty("totalCaseAmount")
            public void setTotalCaseAmount(long value) {
                this.totalCaseAmount = value;
            }

            @JsonProperty("overdueStateList")
            public RelateReachable[] getOverdueStateList() {
                return overdueStateList;
            }

            @JsonProperty("overdueStateList")
            public void setOverdueStateList(RelateReachable[] value) {
                this.overdueStateList = value;
            }
        }

// RelateReachable.java


        public static class RelateReachable {
            private String code;
            private String message;

            @JsonProperty("code")
            public String getCode() {
                return code;
            }

            @JsonProperty("code")
            public void setCode(String value) {
                this.code = value;
            }

            @JsonProperty("message")
            public String getMessage() {
                return message;
            }

            @JsonProperty("message")
            public void setMessage(String value) {
                this.message = value;
            }
        }

// LoanCase.java


        public static class LoanCase {
            private String loanName;
            private String batchName;
            private long loanTotalPeriod;
            private long totalShouldRepay;
            private long totalBackedRepay;
            private IsRegister isRegister;
            private boolean isBlock;

            @JsonProperty("loanName")
            public String getLoanName() {
                return loanName;
            }

            @JsonProperty("loanName")
            public void setLoanName(String value) {
                this.loanName = value;
            }

            @JsonProperty("batchName")
            public String getBatchName() {
                return batchName;
            }

            @JsonProperty("batchName")
            public void setBatchName(String value) {
                this.batchName = value;
            }

            @JsonProperty("loanTotalPeriod")
            public long getLoanTotalPeriod() {
                return loanTotalPeriod;
            }

            @JsonProperty("loanTotalPeriod")
            public void setLoanTotalPeriod(long value) {
                this.loanTotalPeriod = value;
            }

            @JsonProperty("totalShouldRepay")
            public long getTotalShouldRepay() {
                return totalShouldRepay;
            }

            @JsonProperty("totalShouldRepay")
            public void setTotalShouldRepay(long value) {
                this.totalShouldRepay = value;
            }

            @JsonProperty("totalBackedRepay")
            public long getTotalBackedRepay() {
                return totalBackedRepay;
            }

            @JsonProperty("totalBackedRepay")
            public void setTotalBackedRepay(long value) {
                this.totalBackedRepay = value;
            }

            @JsonProperty("isRegister")
            public IsRegister getIsRegister() {
                return isRegister;
            }

            @JsonProperty("isRegister")
            public void setIsRegister(IsRegister value) {
                this.isRegister = value;
            }

            @JsonProperty("isBlock")
            public boolean getIsBlock() {
                return isBlock;
            }

            @JsonProperty("isBlock")
            public void setIsBlock(boolean value) {
                this.isBlock = value;
            }
        }

// User.java


        public static class User {
            private String userID;
            private String realName;

            @JsonProperty("userId")
            public String getUserID() {
                return userID;
            }

            @JsonProperty("userId")
            public void setUserID(String value) {
                this.userID = value;
            }

            @JsonProperty("realName")
            public String getRealName() {
                return realName;
            }

            @JsonProperty("realName")
            public void setRealName(String value) {
                this.realName = value;
            }
        }
    }
}
