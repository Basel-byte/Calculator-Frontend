package com.calculatorapplication.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Evaluator {

    private String expression;

    public Evaluator(String expression) {
        this.expression = expression;
    }

    public String evaluateExpression()  {
        this.expression = this.expression.replaceAll("%", "/100");
        this.expression = this.expression.replaceAll("×", "*");
        this.expression = this.expression.replaceAll("÷", "/");
        if(this.expression.charAt(0) == '-')
            this.expression = this.expression.replaceFirst("-", "1*-");
        for (int i = 1; i < this.expression.length() - 1; i++) {
            if(this.expression.charAt(i) == '-' && !isOperator(String.valueOf(this.expression.charAt(i + 1)))&&
                    !isOperator(String.valueOf(this.expression.charAt(i - 1))))
                this.expression = this.expression.substring(0, i) + "+" + this.expression.substring(i);
        }


        List<String> postfix = infixToPostfix();
        Stack<String> evalStack = new Stack<>();
        double temp1;
        double temp2;
        double result;

        for (int i = 0; i < postfix.size(); i++) {
            if(!isOperator(postfix.get(i))) {
                if(postfix.get(i).charAt(0) == '√') {
                    String temp = postfix.get(i).replace("√", "");
                    double sqrResult = evaluateOperation(Double.parseDouble(temp),0,"√");
                    postfix.set(i, String.valueOf(sqrResult));
                }
                evalStack.push(postfix.get(i));
            }
            else {
                temp1 = Double.parseDouble(evalStack.pop());
//                System.out.println(temp1);
                temp2 = Double.parseDouble(evalStack.pop());
//                System.out.println(temp2);
                result = evaluateOperation(temp2, temp1, postfix.get(i));
//                System.out.println(result);
                evalStack.push(String.valueOf(result));
            }
        }
        return evalStack.pop();
    }

    public double evaluateOperation(double n1, double n2, String ch) {

        switch (ch) {
            case "+":
                return n1 + n2;
            case "-":
                return n1 - n2;
            case "*":
                return n1 * n2;
            case "/":
                return n1 / n2;
            case "^":
                return Math.pow(n1, n2);
            case "√":
                return Math.sqrt(n1);
        }
        return 0;
    }


    public int precedence(String ch) {
        switch (ch) {
            case "+":
            case "-":
                return 1;
            case "*":
            case "/":
                return 2;
            case "^":
                return 3;
            default:
                return -1;
        }
    }

    public boolean isOperator(String ch) {
        return ch.equals("+") || ch.equals("-") || ch.equals("/") || ch.equals("*") || ch.equals("^");
    }

    public List<String> listOperators() {
        List<String> operators = new ArrayList<>();
        for (int i = 0; i < this.expression.length() - 1; i++) {
            if(isOperator(String.valueOf(this.expression.charAt(i)))) {
                operators.add(String.valueOf(this.expression.charAt(i)));
                if(this.expression.charAt(i+1) == '-')
                    i++;
//                System.out.println(this.expression.charAt(i));
            }
        }
        return operators;
    }

    public List<String> infixToPostfix() {
        String []operands = this.expression.split("\\+|\\*|\\/|\\^");;
        List<String> postfix = new ArrayList<>();
        Stack<String> stack = new Stack<>();
        List<String> operators = listOperators();
        int length = operands.length + operators.size();

        int left = 0, right = 0;
        for (int i = 0; i < length; i++) {
            if (i % 2 == 0) {
                postfix.add(operands[left]);
                left++;
            }
            else {
                while (!stack.empty() && precedence(operators.get(right)) <= precedence(stack.peek())) {
                    postfix.add(stack.pop());
                }
                stack.push(operators.get(right));
                right++;
            }
        }
        while(!stack.empty()) {
            postfix.add(stack.pop());
        }
        return postfix;
    }
}
