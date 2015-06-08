package com.kartikshah.coursera.crypto1.week5;

import org.junit.Test;

import java.math.BigInteger;

/**
 * Created by kartik on 2/21/15.
 */
public class TestDLogCalculator
{
    @Test
    public void testCalculateDLog(){
        DLogCalculator calculator = new DLogCalculator();
        BigInteger p = new BigInteger("13407807929942597099574024998205846127479365820592393377723561443721764030073546976801874298166903427690031858186486050853753882811946569946433649006084171");
        BigInteger h = new BigInteger("3239475104050450443565264378728065788649097520952449527834792452971981976143292558073856937958553180532878928001494706097394108577585732452307673444020333");
        BigInteger g = new BigInteger("11717829880366207009516117596335367088558084999998952205599979459063929499736583746670572176471460312928594829675428279466566527115212748467589894601965568");
        BigInteger x = calculator.calculateDLog(p,h,g);


        BigInteger hx = g.modPow(x,p).mod(p);
        System.out.println(hx);
        System.out.println(h.mod(p));

    }
}
