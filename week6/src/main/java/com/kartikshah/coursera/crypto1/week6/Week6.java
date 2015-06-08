package com.kartikshah.coursera.crypto1.week6;

import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;

/**
 * Created by kartik on 2/28/15.
 */
public class Week6
{
    public void question1(){
        BigInteger N = new BigInteger("17976931348623159077293051907890247336179769789423065727343008" +
                "11577326758055056206869853794492129829595855013875371640157101398586" +
                "47833778606925583497541085196591615128057575940752635007475935288" +
                "71082364994994077189561705436114947486504671101510156394068052754" +
                "0071584560878577663743040086340742855278549092581");

        BigInteger A = sqrt(N).add(BigInteger.ONE);
        System.out.println("A^2 = " +A.pow(2));

        BigInteger x = sqrt(A.pow(2).add(N.negate()));
        System.out.println("x = " +x);
        BigInteger p = A.add(x.negate());
        BigInteger q = A.add(x);
        System.out.println("p = " + p);
        System.out.println("q = " + q);
        System.out.println("N =" + p.multiply(q));
        System.out.println("Org N = " + N);
    }

    public void question2(){
        BigInteger N = new BigInteger("6484558428080716696628242653467722787263437207069762630604390703787" +
                "9730861808111646271401527606141756919558732184025452065542490671989" +
                "2428844841839353281972988531310511738648965962582821502504990264452" +
                "1008852816733037111422964210278402893076574586452336833570778346897" +
                "15838646088239640236866252211790085787877");
        BigInteger A = sqrt(N);

        BigInteger x = BigInteger.ZERO;
        while (true){

             x = sqrt(A.pow(2).add(N.negate()));

            if (N.compareTo(A.add(x.negate()).multiply(A.add(x)))==0){
                break;
            }
            A = A.add(BigInteger.ONE);

        }
        System.out.println("question2");
        System.out.println("p = " + A.add(x.negate()));
    }


    public void question3(){

        BigInteger N = new BigInteger("72006226374735042527956443552558373833808445147399984182665305798191"+
                "63556901883377904234086641876639384851752649940178970835240791356868" +
                "77441155132015188279331812309091996246361896836573643119174094961348" +
                "52463970788523879939683923036467667022162701835329944324119217381272" +
                "9276147530748597302192751375739387929");
        BigInteger A = sqrt(N.multiply(new BigInteger("24"))).add(BigInteger.ONE);
        BigInteger Minus24N = N.multiply(new BigInteger("24")).negate();
        BigInteger x = sqrt(A.pow(2).add(Minus24N));

        BigInteger first = A.add(x.negate());
        BigInteger second = A.add(x);

        System.out.println(first);
        System.out.println(second);

        boolean isFirstFactorEven = first.divide(new BigInteger("2")).mod(new BigInteger("2")).compareTo(BigInteger.ZERO) == 0;
        boolean isSecondFactorEven = second.divide(new BigInteger("2")).mod(new BigInteger("2")).compareTo(BigInteger.ZERO) == 0;

        BigInteger p = null;
        BigInteger q = null;

        if(isFirstFactorEven && !isSecondFactorEven) {
            q = first.divide(new BigInteger("4"));
            p = second.divide(new BigInteger("6"));
        }else if (!isFirstFactorEven && isSecondFactorEven) {
            q = second.divide(new BigInteger("4"));
            p = first.divide(new BigInteger("6"));
        }

        if (p.compareTo(q) < 0){
            System.out.println("P" + p);
        }else{
            System.out.println("Q" + q);
        }

        BigInteger pq = p.multiply(q);
        System.out.println(N);
        System.out.println(pq);
        System.out.println(N.compareTo(pq) == 0);
//P393481039457333496832134840884531548740614911572962598045135494489815645318617191045711230705277210672434807384032925190103361097600478829986506046425965140
//P130562844347627099724846978505524077952828798452823449683942411480289100381103330634081349299711487395165652233785936795007352186282088988520340096271157564


    }

    public void question4() throws UnsupportedEncodingException
    {

        BigInteger cipherText = new BigInteger("22096451867410381776306561134883418017410069787892831071731839143676135600120538004282329650473509424343946219751512256465839967942889460764542040581564748988013734864120452325229320176487916666402997509188729971690526083222067771600019329260870009579993724077458967773697817571267229951148662959627934791540");
        BigInteger N = new BigInteger("17976931348623159077293051907890247336179769789423065727343008" +
                "11577326758055056206869853794492129829595855013875371640157101398586" +
                "47833778606925583497541085196591615128057575940752635007475935288" +
                "71082364994994077189561705436114947486504671101510156394068052754" +
                "0071584560878577663743040086340742855278549092581");

        BigInteger e = new BigInteger("65537");

        BigInteger A = sqrt(N).add(BigInteger.ONE);
        BigInteger x = sqrt(A.pow(2).add(N.negate()));
        BigInteger p = A.add(x.negate());
        BigInteger q = A.add(x);

        System.out.println("p = " + p);
        System.out.println("q = " + q);

        BigInteger fx = (p.add(BigInteger.ONE.negate())).multiply(q.add(BigInteger.ONE.negate()));

        BigInteger d = e.modInverse(fx);

        BigInteger value = cipherText.modPow(d,N);

        String valueHexStr = value.toString(16);
        String valueFinal = valueHexStr.substring(valueHexStr.indexOf("00")+2);

        System.out.println(new String(DatatypeConverter.parseHexBinary(valueFinal), "ISO-8859-1"));


    }

//http://faruk.akgul.org/blog/javas-missing-algorithm-biginteger-sqrt/
    BigInteger sqrt(BigInteger n) {
      BigInteger a = BigInteger.ONE;
      BigInteger b = new BigInteger(n.shiftRight(5).add(new BigInteger("8")).toString());
      while(b.compareTo(a) >= 0) {
        BigInteger mid = new BigInteger(a.add(b).shiftRight(1).toString());
        if(mid.multiply(mid).compareTo(n) > 0) b = mid.subtract(BigInteger.ONE);
        else a = mid.add(BigInteger.ONE);
      }
      return a.subtract(BigInteger.ONE);
    }
}
