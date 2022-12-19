package com.girover.database;

import java.sql.Connection;
import java.util.ArrayList;

import com.girover.database.eloquent.EloquentBuilder;

public class QueryBuilder {

    /**
     * The database connection instance.
     */
    public Connection connection;

    /**
     * The database query grammar instance.
     *
     * @var \Illuminate\Database\Query\Grammars\Grammar
     */
    public String grammar;

    /**
     * The database query post processor instance.
     *
     * @var \Illuminate\Database\Query\Processors\Processor
     */
    public String processor;

    /**
     * The current query value bindings.
     *
     * @var array
     */
    public ArrayList<Object> selectBindings = new ArrayList<>();
    public ArrayList<Object> fromBindings = new ArrayList<>();
    public ArrayList<Object> joinBindings = new ArrayList<>();
    public ArrayList<Object> whereBindings = new ArrayList<>();
    public ArrayList<Object> groupByBindings = new ArrayList<>();
    public ArrayList<Object> havingBindings = new ArrayList<>();
    public ArrayList<Object> orderBindings = new ArrayList<>();
    public ArrayList<Object> unionBindings = new ArrayList<>();
    public ArrayList<Object> unionOrderBindings = new ArrayList<>();
    /**
     * An aggregate function and column to be run.
     *
     * @var array
     */
    public ArrayList<Object> aggregate = new ArrayList<>();

    /**
     * The columns that should be returned.
     *
     * @var array
     */
    public ArrayList<Object> columns = new ArrayList<>();

    /**
     * Indicates if the query returns distinct results.
     *
     * Occasionally contains the columns that should be distinct.
     *
     * @var bool|array
     */
    public Object distinct = false;

    /**
     * The table which the query is targeting.
     *
     * @var string
     */
    public String from;

    /**
     * The table joins for the query.
     *
     * @var array
     */
    public ArrayList<Object> joins = new ArrayList<>();

    /**
     * The where constraints for the query.
     *
     * @var array
     */
    public ArrayList<Object> wheres = new ArrayList<>();

    /**
     * The groupings for the query.
     *
     * @var array
     */
    public ArrayList<Object> groups = new ArrayList<>();

    /**
     * The having constraints for the query.
     *
     * @var array
     */
    public ArrayList<Object> havings = new ArrayList<>();

    /**
     * The orderings for the query.
     *
     * @var array
     */
    public ArrayList<Object> orders = new ArrayList<>();

    /**
     * The maximum number of records to return.
     *
     * @var int
     */
    public int limit;

    /**
     * The number of records to skip.
     *
     * @var int
     */
    public int offset;

    /**
     * The query union statements.
     *
     * @var array
     */
    public ArrayList<Object> unions = new ArrayList<>();

    /**
     * The maximum number of union records to return.
     *
     * @var int
     */
    public int unionLimit;

    /**
     * The number of union records to skip.
     *
     * @var int
     */
    public int unionOffset;

    /**
     * The orderings for the union query.
     *
     * @var array
     */
    public ArrayList<Object> unionOrders = new ArrayList<>();

    /**
     * Indicates whether row locking is being used.
     *
     * @var string|bool
     */
    public boolean lock;

    /**
     * The callbacks that should be invoked before the query is executed.
     *
     * @var array
     */
    public ArrayList<Object> beforeQueryCallbacks = new ArrayList<>();

    /**
     * All of the available clause operators.
     *
     * @var string[]
     */
    public String[] operators = {
        "=", "<", ">", "<=", ">=", "<>", "!=", "<=>",
        "like", "like binary", "not like", "ilike",
        "&", "|", "^", "<<", ">>", "&~", "is", "is not",
        "rlike", "not rlike", "regexp", "not regexp",
        "~", "~*", "!~", "!~*", "similar to",
        "not similar to", "not ilike", "~~*", "!~~*",
    };

    /**
     * All of the available bitwise operators.
     *
     * @var string[]
     */
    public String[] bitwiseOperators = {
        "&", "|", "^", "<<", ">>", "&~",
    };

    public QueryBuilder() {
    }
//    
//    public QueryBuilder(Connection connection) {
//    	this.connection = connection;
//    }
    
    public QueryBuilder select(Object...columns) {
    	
    	this.columns.clear();
    	this.selectBindings.clear();
    	
    	for (Object column : columns) {
    		if(isQueryable(column))
    			System.out.println("");
    		
    		if(column.getClass().equals(String.class))
    			this.columns.add(column);
		}
    	
    	return this;
    }
    
    public QueryBuilder from(Object table, String as) {
    	
    	if(isQueryable(table)) {
    		
    	}else {
    		this.from = (as == null) ? (String)table : (String)table + " AS " + as;
    	}
    	
    	return this;
    }
    
    public QueryBuilder join(String table, String first, String operation, String second) {
    	
    	
    	return this;
    }
    
    /**
     * Determine if the value is a query builder instance or a Closure.
     *
     * @param  mixed  $value
     * @return bool
     */
    protected boolean isQueryable(Object value)
    {
        return value instanceof QueryBuilder ||
               value instanceof EloquentBuilder;
//               value instanceof Relation ||
//               value instanceof Closure;
    }
}
