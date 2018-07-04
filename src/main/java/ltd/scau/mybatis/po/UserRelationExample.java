package ltd.scau.mybatis.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserRelationExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserRelationExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andUserAIdIsNull() {
            addCriterion("user_a_id is null");
            return (Criteria) this;
        }

        public Criteria andUserAIdIsNotNull() {
            addCriterion("user_a_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserAIdEqualTo(Long value) {
            addCriterion("user_a_id =", value, "userAId");
            return (Criteria) this;
        }

        public Criteria andUserAIdNotEqualTo(Long value) {
            addCriterion("user_a_id <>", value, "userAId");
            return (Criteria) this;
        }

        public Criteria andUserAIdGreaterThan(Long value) {
            addCriterion("user_a_id >", value, "userAId");
            return (Criteria) this;
        }

        public Criteria andUserAIdGreaterThanOrEqualTo(Long value) {
            addCriterion("user_a_id >=", value, "userAId");
            return (Criteria) this;
        }

        public Criteria andUserAIdLessThan(Long value) {
            addCriterion("user_a_id <", value, "userAId");
            return (Criteria) this;
        }

        public Criteria andUserAIdLessThanOrEqualTo(Long value) {
            addCriterion("user_a_id <=", value, "userAId");
            return (Criteria) this;
        }

        public Criteria andUserAIdIn(List<Long> values) {
            addCriterion("user_a_id in", values, "userAId");
            return (Criteria) this;
        }

        public Criteria andUserAIdNotIn(List<Long> values) {
            addCriterion("user_a_id not in", values, "userAId");
            return (Criteria) this;
        }

        public Criteria andUserAIdBetween(Long value1, Long value2) {
            addCriterion("user_a_id between", value1, value2, "userAId");
            return (Criteria) this;
        }

        public Criteria andUserAIdNotBetween(Long value1, Long value2) {
            addCriterion("user_a_id not between", value1, value2, "userAId");
            return (Criteria) this;
        }

        public Criteria andUserBIdIsNull() {
            addCriterion("user_b_id is null");
            return (Criteria) this;
        }

        public Criteria andUserBIdIsNotNull() {
            addCriterion("user_b_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserBIdEqualTo(Long value) {
            addCriterion("user_b_id =", value, "userBId");
            return (Criteria) this;
        }

        public Criteria andUserBIdNotEqualTo(Long value) {
            addCriterion("user_b_id <>", value, "userBId");
            return (Criteria) this;
        }

        public Criteria andUserBIdGreaterThan(Long value) {
            addCriterion("user_b_id >", value, "userBId");
            return (Criteria) this;
        }

        public Criteria andUserBIdGreaterThanOrEqualTo(Long value) {
            addCriterion("user_b_id >=", value, "userBId");
            return (Criteria) this;
        }

        public Criteria andUserBIdLessThan(Long value) {
            addCriterion("user_b_id <", value, "userBId");
            return (Criteria) this;
        }

        public Criteria andUserBIdLessThanOrEqualTo(Long value) {
            addCriterion("user_b_id <=", value, "userBId");
            return (Criteria) this;
        }

        public Criteria andUserBIdIn(List<Long> values) {
            addCriterion("user_b_id in", values, "userBId");
            return (Criteria) this;
        }

        public Criteria andUserBIdNotIn(List<Long> values) {
            addCriterion("user_b_id not in", values, "userBId");
            return (Criteria) this;
        }

        public Criteria andUserBIdBetween(Long value1, Long value2) {
            addCriterion("user_b_id between", value1, value2, "userBId");
            return (Criteria) this;
        }

        public Criteria andUserBIdNotBetween(Long value1, Long value2) {
            addCriterion("user_b_id not between", value1, value2, "userBId");
            return (Criteria) this;
        }

        public Criteria andRelationIsNull() {
            addCriterion("relation is null");
            return (Criteria) this;
        }

        public Criteria andRelationIsNotNull() {
            addCriterion("relation is not null");
            return (Criteria) this;
        }

        public Criteria andRelationEqualTo(Integer value) {
            addCriterion("relation =", value, "relation");
            return (Criteria) this;
        }

        public Criteria andRelationNotEqualTo(Integer value) {
            addCriterion("relation <>", value, "relation");
            return (Criteria) this;
        }

        public Criteria andRelationGreaterThan(Integer value) {
            addCriterion("relation >", value, "relation");
            return (Criteria) this;
        }

        public Criteria andRelationGreaterThanOrEqualTo(Integer value) {
            addCriterion("relation >=", value, "relation");
            return (Criteria) this;
        }

        public Criteria andRelationLessThan(Integer value) {
            addCriterion("relation <", value, "relation");
            return (Criteria) this;
        }

        public Criteria andRelationLessThanOrEqualTo(Integer value) {
            addCriterion("relation <=", value, "relation");
            return (Criteria) this;
        }

        public Criteria andRelationIn(List<Integer> values) {
            addCriterion("relation in", values, "relation");
            return (Criteria) this;
        }

        public Criteria andRelationNotIn(List<Integer> values) {
            addCriterion("relation not in", values, "relation");
            return (Criteria) this;
        }

        public Criteria andRelationBetween(Integer value1, Integer value2) {
            addCriterion("relation between", value1, value2, "relation");
            return (Criteria) this;
        }

        public Criteria andRelationNotBetween(Integer value1, Integer value2) {
            addCriterion("relation not between", value1, value2, "relation");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}