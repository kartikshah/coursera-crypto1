package com.kartikshah.coursera.crypto1.week6;

import org.junit.Test;

import java.io.UnsupportedEncodingException;

/**
 * Created by kartik on 2/28/15.
 */
public class TestWeek6
{
    @Test
    public void testQuestion1()
    {
        Week6 week6 = new Week6();
        week6.question1();
        //13407807929942597099574024998205846127479365820592393377723561443721764030073662768891111614362326998675040546094339320838419523375986027530441562135724301
    }

    @Test
    public void testQuestion2()
    {
        Week6 week6 = new Week6();
        week6.question2();
        //25464796146996183438008816563973942229341454268524157846328581927885777969985222835143851073249573454107384461557193173304497244814071505790566593206419759
    }

    @Test
    public void testQuestion3()
    {
        Week6 week6 = new Week6();
        week6.question3();
        //130562844347627099724846978505524077952828798452823449683942411480289100381103330634081349299711487395165652233785936795007352186282088988520340096271157564
    }

    @Test
    public void testQuestion4() throws UnsupportedEncodingException
    {
        Week6 week6 = new Week6();
        week6.question4();
        //Factoring lets us break RSA.
    }

}
