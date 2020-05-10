package com.deutsche.tradestore.businessRules;

import java.util.ArrayList;
import java.util.List;

public class RuleEngine {
	
		private List<Rule> rules =  new ArrayList<Rule>();
	
		public static RuleEngine newInstance() {
			return new RuleEngine();
		}
		
		public RuleEngine addRule(Rule rule) {
			rules.add(rule);
			return this;
		}
		
		public RuleEngine execute() throws TradeException{
			for(Rule rule: rules) {
				rule.validate();
			}
			return this;
		}
		
}
