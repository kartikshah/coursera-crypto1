package com.kartikshah.coursera.crypto1.week5;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;

/**
 * Created by kartik on 2/21/15.
 */
public class DLogCalculator
{
    public BigInteger calculateDLog(BigInteger p, BigInteger h, BigInteger g)
    {

        HashMap<BigInteger, BigInteger> leftHandSide = new HashMap<>();
//        HashMap<BigInteger, BigInteger> rightHandSide = new HashMap<>();

        BigInteger solutionX1 = BigInteger.ZERO;
        BigInteger solutionX0 = BigInteger.ZERO;
        BigInteger solutionX;


        BigInteger B = new BigInteger("2").pow(20);
        for (BigInteger x1 = BigInteger.ZERO; x1.compareTo(B) < 0; x1 = x1.add(BigInteger.ONE)){
            BigInteger gx1 = g.modPow(x1, p);
            leftHandSide.put(h.multiply(gx1.modInverse(p)).mod(p), x1);
        }

        for (BigInteger x0 = BigInteger.ZERO; x0.compareTo(B) < 0; x0 = x0.add(BigInteger.ONE)){
            BigInteger gbx0 = g.modPow(B, p).modPow(x0,p);
            if (leftHandSide.get(gbx0) != null){
                solutionX1 = leftHandSide.get(gbx0);
                solutionX0 = x0;
                System.out.println(solutionX0 +"\n" + solutionX1);
                break;
            }
        }

        solutionX = solutionX0.multiply(B).add(solutionX1);

        System.out.println(solutionX);
        return solutionX;
    }



}
