package testTheList;
/*
 * Der JUnit-Test fuer A6x3
 */

// Internal Note: Replace
// ======================
// either  *##/--]    ->    */
// or           */    ->    *##/--]


import static org.junit.Assert.*;
//
import org.junit.Before;
import org.junit.Test;
import java.util.Map;
import java.util.HashMap;
//
//
//#############################################################################
//###
//###   Bei den nachfolgenden IMPORTs sind vermutlich Anpassungen noetig
//###   ================================================================
//###
//
//Import von "AUDIO", "CD", "Disc", "DVD". "MOVIE", "NTSC", "PAL", "VIDEO"
import static media.Content.*;
import static media.Format.*;
import media.*;
//
//Import von "MyList"
import list.MyList;


/**
 * The VERY TestFrame - the official testframe deciding over acceptance of not
 *
 * JUnit-Test fuer die MultiPurposeList.
 * Die ist ein Abnahmetest, der (urspruenglich) bewusst keine Unterstuetzung bei der Fehlersuche gibt.
 * Testen ist Aufgabe des Produzenten und NICHT des Kunden.
 * 
 * @author   Michael Schaefers ; P1@Hamburg-UAS.eu 
 * @version  2017/12/03
 */
public class UnitTestFrame {
    
    @Before
    public void selfTestIntegrity(){
        
        // check  of data
        if(     ( numberOfTestSets != 3 )
            ||  ( numberOfTestSets != testData.length )
        ){
            throw new IllegalStateException( String.format( "INTERNAL ERROR : inconsistent test data" ) );
        }//if
        //
        // integrity check -> check if all test sets have same size
        final int testSetSize = testData[0].length;
        if(     ( testSetSize != testData[1].length )
            ||  ( testSetSize != testData[2].length )
        ){
            throw new IllegalStateException( String.format( "INTERNAL ERROR : inconsistent test data" ) );
        }//if
        
        for( int i1=0; i1<numberOfTestSets; i1++ ){
            for( int i2=0; i2<testData[0].length; i2++ ){
                for( int i3=1; i3<numberOfTestSets; i3++ ){
                    if( ! testData[i1][i2].equals( testData[(i1+i3) % numberOfTestSets][i2] ))  throw new IllegalStateException( "INTERNAL ERROR : test set integrity broken [equals()]" );
                    if( testData[i1][i2] == testData[(i1+i3) % numberOfTestSets][i2] )  throw new IllegalStateException( "INTERNAL ERROR : integrity broken [==]" );
                }//for
                for( int i3=1; i3<testData[0].length; i3++ ){
                    if( testData[i1][i2].equals( testData[i1][(i2+i3) % testData[0].length] ))  throw new IllegalStateException( "INTERNAL ERROR : test set integrity broken [equals()]" );
                }//for
            }//for
        }//for
        
    }//method()
    
    
    
    //##########################################################################
    /**
     * basic test : testing isEmpty() primarily,
     * but also using and testing in some way:<br />
     * o) putL()<br />
     * o) removeNo(0)<br />
     *
     * Additional heavy tests of getSize are done in the random based copy around tests
     * 
     * @author Michael Schaefers
     * @version 16/11/30
     */
    @Test( timeout = commonLimit )
    public void basicTest_isEmpty(){
        final MyList<Object> list = new MyList<Object>();
        assertTrue( list.isEmpty() );                                           // isEmpty() main focus
        
        list.putL( 1 );                                                         // using "putL()" and hence, testing it
        assertFalse( list.isEmpty() );                                          // isEmpty() main focus
        
        list.removeNo( 0 );                                                     // 
        assertTrue( list.isEmpty() );                                           // isEmpty() main focus
    }//method()
    
    
    
    //##########################################################################
    /**
     * basic test : testing getSize() primarily,
     * but also using and testing in some way:<br />
     * o) putL()<br />
     * o) removeNo(0)<br />
     * o) isEmpty()<br />
     *
     * Additional heavy tests of getSize() are done in the random based copy around tests
     * 
     * @author Michael Schaefers
     * @version 16/11/30
     */
    @Test( timeout = commonLimit )
    public void basicTest_getSize(){
        final MyList<Object> list = new MyList<Object>();
        int noElem = 0;
        assertEquals( noElem,  list.getSize() );                                // getSize() main focus
        
        do{
            list.putL( noElem );                                                // using "putL()" and hence, testing it
            noElem++;
            assertEquals( noElem,  list.getSize() );                            // getSize() main focus
        }while( noElem<42 );                                                    // just any number - and "42" is the answer ;-)
        //
        for( int i=0; i < 13; i++ ){
            list.removeNo( 0 );                                                 // using "removeNo(0)" and hence, testing it
            noElem--;
            assertEquals( noElem,  list.getSize() );                            // getSize() main focus
        }//for
        
        while( ! list.isEmpty() ){                                              // using "isEmpty()" and hence, testing it
            list.removeNo( 0 );                                                 // ..... "removeNo(0)" ...
            noElem--;
            assertEquals( noElem,  list.getSize() );                            // getSize() main focus
        }//while
    }//method()
    
    
    
    //##########################################################################
    /**
     * basic test : testing contains() primarily,
     * but also using and testing in some way:<br />
     * o) putL()<br />
     * 
     * @author Michael Schaefers
     * @version 16/11/30
     */
    @Test( timeout = commonLimit )
    public void basicTest_contains(){
        final MyList<Object> list = new MyList<Object>();
        
        for( int i=1; i<42; i++ ){
            final Object currentObject = i;
            list.putL( currentObject );                                         // using "putL()" and hence, testing it
            assertTrue( list.contains( currentObject ) );                       // contains() main focus
        }//for
        
        assertFalse( list.contains( 0 ) );                                      // contains() main focus
        
        for( Integer i=1_000; i<1_042; i++ ){
            String sOrg = i.toString();
            String sCpy = i.toString();
            assert sOrg!=sCpy;
            list.putL( sOrg );                                                  // ..... "putL()" ...
            assertTrue( list.contains( sCpy ) );                                // contains() main focus
        }//for
    }//method()
    
    
    
    //##########################################################################
    /**
     * basic test : testing clear() primarily,
     * but also using and testing in some way:<br />
     * o) contains()<br />
     * o) getSize()<br />
     * o) isEmpty()<br />
     * o) putL()<br />
     * 
     * @author Michael Schaefers
     * @version 16/11/30
     */
    @Test( timeout = commonLimit )
    public void basicTest_clear(){
        final MyList<Object> list = new MyList<Object>();
        
        for( int i=0; i<10; i++ ){
            list.putL( 1 );                                                     // using "putL()" and hence, testing it
        }//for
        
        list.clear();                                                           // clear() main focus 
        
        assertTrue( list.isEmpty() );                                           // using "isEmpty()" and hence, testing it
        assertEquals( 0, list.getSize() );                                      // using "getSize()" and hence, testing it
        for( int i=0; i<10; i++ ){
            assertFalse( list.contains( i ) );                                  // using "contains()" and hence, testing it
        }//for
    }//method()
    
    
    
    //##########################################################################
    /**
     * basic test : testing putNo() primarily,
     * but also using and testing in some way:<br />
     * o) contains()<br />
     *
     * Additional heavy tests of putNo() are done in the random based copy around tests
     * 
     * @author Michael Schaefers
     * @version 16/11/30
     */
    @Test( timeout = commonLimit )
    public void basicTest_putNo(){
        final MyList<Object> list = new MyList<Object>();
        
        for( int i=0; i<10; i++ ){
            final Object currentObject = i;
            list.putNo( i, currentObject );                                     // "putNo(last)" main focus
            assertTrue( list.contains( currentObject ) );                       // using "contains()" and hence, testing it
        }//for
        
        for( int i=10; i<20; i++ ){
            final Object currentObject = i;
            list.putNo( 5, currentObject );                                     // "putNo("middle")" main focus
            assertTrue( list.contains( currentObject ) );                       // ..... "contains()" ...
        }//for
        
        for( int i=0; i<10; i++ ){
            final Object currentObject = (20+i);
            list.putNo( 1+2*i, currentObject );                                 // "putNo("middle")" main focus
            assertTrue( list.contains( currentObject ) );                       // ..... "contains()" ...
        }//for
        
        for( int i=30; i<40; i++ ){
            final Object currentObject = i;
            list.putNo( 0, currentObject );                                     // "putNo(0)" main focus
            assertTrue( list.contains( currentObject ) );                       // ..... "contains()" ...
        }//for
    }//method()
    
    
    
    //##########################################################################
    /**
     * basic test : testing getNo() primarily,
     * but also using and testing in some way:<br />
     * o) putNo()<br />
     *
     * Additional heavy tests of getNo() are done in the random based copy around tests
     * 
     * @author Michael Schaefers
     * @version 16/11/30
     */
    @Test( timeout = commonLimit )
    public void basicTest_getNo(){
        final MyList<Object> list = new MyList<Object>();
        
        Object currentObject = null;
        for( int i=0; i<42; i++ ){
            currentObject = i;
            list.putNo( i, currentObject );                                     // using "putNo()" and hence, testing it
            assertTrue( currentObject.equals( list.getNo( i ) ));              // "getNo()" main focus
        }//for
        
        for( int i=42; --i>=0; ){
            currentObject = i;
            assertTrue( currentObject.equals( list.getNo( i ) ));              // "getNo()" main focus
        }//for
    }//method()
    
    
    
    //##########################################################################
    /**
     * basic test : testing removeNo() primarily,
     * but also using and testing in some way:<br />
     * o) contains()<br />
     * o) putNo()<br />
     *
     * Additional heavy tests of removeNo() are done in the random based copy around tests
     * 
     * @author Michael Schaefers
     * @version 16/11/30
     */
    @Test( timeout = commonLimit )
    public void basicTest_removeNo(){
        final MyList<Object> list = new MyList<Object>();
        
        Object currentObject = null;
        for( int i=0; i<42; i++ ){
            currentObject = i;
            list.putNo( i, currentObject );                                     // using "putNo()" and hence, testing it
        }//for
        
        for( int i=40; i>=0; i-=5 ){
            currentObject = i;
            assertTrue( list.contains( currentObject ) );                       // using "contains()" and hence, testing it
            list.removeNo( i );                                                 // "removeNo()" main focus
            assertFalse( list.contains( currentObject ) );                      // ..... "contains()" ...
        }//for
    }//method()
    
    
    
    //##########################################################################
    /**
     * basic test : testing remove() primarily,
     * but also using and testing in some way:<br />
     * o) contains()<br />
     * o) putNo()<br />
     * 
     * @author Michael Schaefers
     * @version 16/11/30
     */
    @Test( timeout = commonLimit )
    public void basicTest_remove(){
        {//scope
            final MyList<Object> list = new MyList<Object>();                   // type Object for information object
            
            Object currentObject = null;
            for( int i=0; i<42; i++ ){
                currentObject = i;
                list.putNo( i, currentObject );                                 // using "putNo()" and hence, testing it
            }//for
            
            for( int i=40; i>=0; i-=5 ){
                currentObject = i;
                assertTrue( list.contains( currentObject ) );                   // using "contains()" and hence, testing it
                assertTrue( list.remove( currentObject ) );                     // "remove()" main focus
                assertFalse( list.contains( currentObject ) );                  // ..... "contains()" ...
            }//for
            
            for( int i=40; i>=0; i-=5 ){
                currentObject = i;
                assertFalse( list.remove( currentObject ) );                    // "remove()" main focus
            }//for
        }//scope
        
        
        {//scope
            final MyList<String> list = new MyList<String>();                   // type String for information object
            
            for( Integer i=0; i<42; i++ ){
                final String currentString = i.toString();
                list.putNo( i, currentString );                                 // ..... "putNo()" ...
            }//for
            
            for( Integer i=40; i>=0; i-=5 ){
                final String currentString = i.toString();
                assertTrue( list.contains( currentString ) );                   // ..... "contains()" ...
                assertTrue( list.remove( currentString ) );                     // "remove()" main focus
                assertFalse( list.contains( currentString ) );                  // ..... "contains()" ...
            }//for
            
            for( Integer i=40; i>=0; i-=5 ){
                final String currentString = i.toString();
                assertFalse( list.remove( currentString ) );                    // "remove()" main focus
            }//for
        }//scope
    }//method()
    
    
    
    //##########################################################################
    /**
     * basic test : testing setNo() primarily,
     * but also using and testing in some way:<br />
     * o) contains()<br />
     * o) putNo()<br />
     *
     * Additional heavy tests of setNo() are done in the random based copy around tests
     * 
     * @author Michael Schaefers
     * @version 16/11/30
     */
    @Test( timeout = commonLimit )
    public void basicTest_setNo(){
        final MyList<Integer> list = new MyList<Integer>();
        
        for( int i=0; i<10; i++ ){
            final Integer currentInteger = i;
            list.putNo( i, currentInteger );                                    // using "putNo()" and hence, testing it
        }//for
        
        for( int i=0; i<10; i++ ){
            final Integer currentOldInteger = i;
            final Integer currentNewInteger = (i+1000);
            assertTrue(  list.contains( currentOldInteger ) );                  // using "contains()" and hence, testing it
            assertFalse( list.contains( currentNewInteger ) );                  // ..... contains() ...
            final Integer replacedInteger = list.setNo( i, currentNewInteger ); // "setNo()" main focus
            assertEquals( replacedInteger, currentOldInteger );
            assertFalse( list.contains( currentOldInteger ) );                  // ..... "contains()" ...
            assertTrue(  list.contains( currentNewInteger ) );                  // ..... "contains()" ...
        }//for
    }//method()
    
    
    
    //##########################################################################
    /**
     * basic test : testing simple access sequence
     * but also using and testing in some way:<br />
     * o) contains()<br />
     * o) putNo()<br />
     * o) removeNo()<br />
     *
     * trying to entrap usage of prev pointer to detect errors related to prev pointer
     * test is from Axel Schmolitzky, since combinedTest_copyAround_randomBased() shall NOT have detected such error
     *     
     * @author Michael Schaefers (based on code from Axel Schmolitzky)
     * @version 16/12/07
     */
    @Test( timeout = commonLimit )
    public void basicTest_simpleAccessSequence(){
        final MyList<Integer> list = new MyList<Integer>();
        list.putNo( 0, 22 );                                                    // using "putNo()" and hence, testing it
        list.putNo( 0, 11 );                                                    // ..... "putNo()" ...
        list.removeNo( 1 );                                                     // using "removeNo()" and hence, testing it
        assertTrue( list.contains( 11 ));                                       // using "contains()" and hence, testing it
    }//method()
    
    
    
    //##########################################################################
    /**
     * Severe positive test : testing combiations of insert-, remove- and extract- operations.
     * This test is random based - meaning, that it's hard to reproduce an exactly same test.
     * If "numberOfRandomBasedTestRuns" is sufficient big, most
     * respectively probably each implementation error will be detected
     *     
     * @author Michael Schaefers
     * @version 16/11/30
     */
    @Test( timeout = commonLimit )
    public void combinedTest_copyAround_randomBased(){
        
        //  - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
        // setup everything
        
        //
        final Disc[] refTestData = testData[0];
        
        // setup empty list
        final MyList<Disc> emptyList = new MyList<Disc>();
        assertEquals(
            "empty list is not empty",
            0,
            emptyList.getSize()
        );
        
        // setup REFerence/original list
        final MyList<Disc> refList = new MyList<Disc>();
        for( Disc disc : refTestData ){
            refList.putL( disc );                                               // using "putL()" and hence, testing it
        }//for
        checkListAgainstArray( refList, refTestData, "test setup: putL()" );    // check, if setup was ok
        checkListAgainstArray( refList, refTestData, "test setup" );            // check, just for the case that problems arose out of check just before -> "getNo()" was called first time ;-)
        
        // setup two work-lists for testing by copying values between same
        final MyList<Disc> list1 = new MyList<Disc>();
        final MyList<Disc> list2 = new MyList<Disc>();
        //
        // list1 as copy of original list
        for ( int index=refList.getSize(); --index>=0; ){                       // using "getSize()" and hence, testing it
            final Disc disc = refList.getNo( index );                           // using "getNo()" and hence, testing it
            list1.putF( disc );                                                 // using "putF()" and hence, testing it
        }//for
        checkListAgainstList( list1, refList, "test setup" );                   // check, if list was copied
        checkListAgainstArray( list1, refTestData, "test setup" );              // unnecessary, but for safety's sake
        
        
        
        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
        // start actual (non random based) testing
        
        // remove at end - insert at front
        while ( list1.getSize() > 0 ){                                          // using "getSize()" and hence, testing it
            final int lastPosition = list1.getSize() -1;                        // ..... "getSize()" ...
            final Disc disc = list1.getNo( lastPosition );                      // ..... "getNo(last)" ...
            list1.removeNo( lastPosition );                                     // using "removeNo(last)" and hence, testing it
            list2.putNo( 0, disc );                                             // using "putNo(0,..)" and hence, testing it
        }//while
        checkListAgainstList( list2, refList,   "remove at end/insert at front" );
        checkListAgainstList( list1, emptyList, "remove at end/insert at front" );
        
        // remove at front - insert at end
        while ( list2.getSize() > 0 ){                                          // ..... "getSize()" ...
            final Disc disc = list2.getNo( 0 );                                 // ..... "getNo(0)" ...
            list2.removeNo( 0 );                                                // ..... "removeNo(0)" ...
            final int lastPosition = list1.getSize();                           // ..... "getSize()" ...
            list1.putNo( lastPosition, disc );                                  // ..... "putNo(last,..)" ...
        }//while
        checkListAgainstList( list1, refList,   "remove at front/insert at end" );
        checkListAgainstList( list2, emptyList, "remove at front/insert at end" );
        
        // remove at end - insert at front
        while ( list1.getSize() > 0 ){                                          // ..... "getSize()" ...
            final Disc disc = list1.getL();                                     // using "getL()" and hence, testing it
            final int lastPosition = list1.getSize() -1;                        // ..... "getSize()" ...
            list1.removeNo( lastPosition );                                     // ..... "removeNo(last)" ...
            list2.putF( disc );                                                 // using "putF()" and hence, testing it
        }//while
        checkListAgainstList( list2, refList,   "remove at end/insert at front" );
        checkListAgainstList( list1, emptyList, "remove at end/insert at front" );
        
        // remove at front - insert at end
        while ( list2.getSize() > 0 ){                                          // ..... "getSize()" ...
            final Disc disc = list2.getF();                                     // using "getF()" and hence, testing it
            list2.removeNo( 0 );                                                // ..... "removeNo(0)" ...
            list1.putL( disc );                                                 // using "putL()" and hence, testing it
        }//while
        checkListAgainstList( list1, refList,   "remove at front/insert at end: getF(),removeNo(0),putL()" );
        checkListAgainstList( list2, emptyList, "remove at front/insert at end: getF(),removeNo(0),putL()" );
        
        // remove at end - insert at front
        while ( list1.getSize() > 0 ){                                          // ..... "getSize()" ...
            final int lastPosition = list1.getSize() -1;                        // ..... "getSize()" ...
            final Disc disc = list1.extractNo( lastPosition );                  // using "extractNo(last)" and hence, testing it
            list2.putF( disc );                                                 // ..... "putF()" ...
        }//while
        checkListAgainstList( list2, refList,   "remove at end/insert at front: extractNo(last),putF()" );
        checkListAgainstList( list1, emptyList, "remove at end/insert at front: extractNo(last),putF()" );
        
        // remove at front - insert at end
        while ( list2.getSize() > 0 ){                                          // ..... "getSize()" ...
            final Disc disc = list2.extractNo( 0 );                             // using "extractNo(0)" and hence, testing it
            list1.putL( disc );                                                 // ..... "putL()" ...
        }//while
        checkListAgainstList( list1, refList,   "remove at front/insert at end: extractNo(0),putL()" );
        checkListAgainstList( list2, emptyList, "remove at front/insert at end: extractNo(0),putL()" );
        
        // remove at end - insert at front
        while ( list1.getSize() > 0 ){                                          // ..... "getSize()" ...
            final Disc disc = list1.extractL();                                 // using "extractL()" and hence, testing it
            list2.putF( disc );                                                 // ..... "putF()" ...
        }//while
        checkListAgainstList( list2, refList,   "remove at end/insert at front: extractL(),putF()" );
        checkListAgainstList( list1, emptyList, "remove at end/insert at front: extractL(),putF()" );
        
        // remove at front - insert at end
        while ( list2.getSize() > 0 ){                                          // ..... "getSize()" ...
            final Disc disc = list2.extractF();                                 // using "extractF()" and hence, testing it
            list1.putL( disc );                                                 // ..... "putL()" ...
        }//while
        checkListAgainstList( list1, refList,   "remove at front/insert at end: extractF(),putL()" );
        checkListAgainstList( list2, emptyList, "remove at front/insert at end: extractF(),putL()" );
        
        
        
        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
        // prepare for random based testing
        // store chracteristic data of reference list in original state map
        final Map<Disc,Integer> originalStateMap = toStateMap( refList );
        //
        Map<Disc,Integer> currentStateMap;
        
        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
        // start of random based testing
        final int numberOfRandomBasedTestRuns = 19;
        for ( int stillToDo=numberOfRandomBasedTestRuns; --stillToDo>=0; ){
            // copy random based from list1 to list2
            while ( list1.getSize() > 0 ){
                final int randomPositionSrc = (int)( Math.random()*(list1.getSize() ));
                final int randomPositionDst = (int)( Math.random()*(1+list2.getSize() ));
                final Disc disc = list1.getNo( randomPositionSrc );
                list1.removeNo( randomPositionSrc );
                list2.putNo( randomPositionDst, disc );
            }//while
            currentStateMap = toStateMap( list2 );
            assertEquals(
                String.format( "random based \"copy\": getNo(),removeNo(),putNo() -> %s differs from %s",  originalStateMap, currentStateMap ),
                originalStateMap,
                currentStateMap
            );
            //
            // copy random based from list2 to list1
            while ( list2.getSize() > 0 ){
                final int randomPositionSrc = (int)( Math.random()*(list2.getSize() ));
                final int randomPositionDst = (int)( Math.random()*(1+list1.getSize() ));
                final Disc disc = list2.extractNo( randomPositionSrc );
                list1.putNo( randomPositionDst, disc );
            }//while
            currentStateMap = toStateMap( list1 );
            assertEquals(
                String.format( "random based \"copy\": extractNo(),putNo() -> %s differs from %s",  originalStateMap, currentStateMap ),
                originalStateMap,
                currentStateMap
            );
        }//for
        
    }//method()
    
    
    
    //##########################################################################
    /**
     * Severe positive test : testing setNo() primarily,
     * ...
     *     
     * @author Michael Schaefers
     * @version 16/11/30
     */
    @Test( timeout = commonLimit )
    public void test_setNo_randomBased(){
        // integrity checks have been executed @Before ;-)
        
        int testSetId = 0;
        final MyList<Disc> list = new MyList<Disc>();
        fillListWithTestData( list, testSetId );
        for( int stillToDo=3; --stillToDo>=0; ){
            
            testSetId = (testSetId+1) % numberOfTestSets;
            for( int index=testData[testSetId].length; --index>=0; ){
                final Disc newDisc = testData[testSetId][index];
                final Disc oldDisc = list.setNo( index, newDisc );
                assertEquals(
                    String.format( "expectation NOT met -> %s differs from %s",  newDisc, oldDisc ),
                    newDisc,
                    oldDisc
                );
                assertTrue(
                    String.format( "expectation NOT met -> %s == %s",  newDisc, oldDisc ),
                    newDisc != oldDisc
                );
            }//for
            
            testSetId = (testSetId+1) % numberOfTestSets;
            for( int index=testData[testSetId].length; --index>=0; ){
                final Disc newDisc = testData[testSetId][index];
                final Disc oldDisc = list.setNo( index, newDisc );
                assertEquals(
                    String.format( "expectation NOT met -> %s differs from %s",  newDisc, oldDisc ),
                    newDisc,
                    oldDisc
                );
                assertTrue(
                    String.format( "expectation NOT met -> %s == %s",  newDisc, oldDisc ),
                    newDisc != oldDisc
                );
            }//for
            
            testSetId = (testSetId+1) % numberOfTestSets;
            for( int index=0; index<testData[testSetId].length; index++ ){
                final Disc newDisc = testData[testSetId][index];
                final Disc oldDisc = list.setNo( index, newDisc );
                assertEquals(
                    String.format( "expectation NOT met -> %s differs from %s",  newDisc, oldDisc ),
                    newDisc,
                    oldDisc
                );
                assertTrue(
                    String.format( "expectation NOT met -> %s == %s",  newDisc, oldDisc ),
                    newDisc != oldDisc
                );
            }//for
            
            testSetId = (testSetId+1) % numberOfTestSets;
            for( int index=0; index<testData[testSetId].length; index++ ){
                final Disc newDisc = testData[testSetId][index];
                final Disc oldDisc = list.setNo( index, newDisc );
                assertEquals(
                    String.format( "expectation NOT met -> %s differs from %s",  newDisc, oldDisc ),
                    newDisc,
                    oldDisc
                );
                assertTrue(
                    String.format( "expectation NOT met -> %s == %s",  newDisc, oldDisc ),
                    newDisc != oldDisc
                );
            }//for
        }//for
    }//method()
    
    
    
    //##########################################################################
    /**
     * Severe positive test : replacing disc by equal but NOT identical discs
     *     
     * @author Michael Schaefers
     * @version 16/11/30
     */
    @Test( timeout = commonLimit )
    public void test_replacingElementsByEqualElements_randomBased(){
        // integrity checks have been executed @Before ;-)
        final int testSetSize = testData[0].length;
        
        final MyList<Disc> listA = new MyList<Disc>();                                              // list in which replacements are done
        final MyList<Disc> listB = new MyList<Disc>();                                              // work list containing new elements
        int testSetId = 0;                                                                          // start/current test set ID
        fillListWithTestData( listA, testSetId );
        final int numberOfRandomBasedTestRuns = 19;
        for( int stillToDo=numberOfRandomBasedTestRuns; --stillToDo>=0; ){
            testSetId = (testSetId+1) % numberOfTestSets;                                           // next test set ID  pointing to differnet test set with equal but NOT identical elements
            fillListWithTestData( listB, testSetId );
            
            // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
            // replace all elements in old list by equal but NOT identical elements
            //
            while ( listB.getSize() > 0 ){
                final int randomPositionB = (int)( Math.random()*listB.getSize() );                 // just some valid position in new list
                final Disc disc = listB.extractNo( randomPositionB );                               // get new element from new list
                assertTrue(
                    String.format( "disc NOT found in list -> %s NOT in %s",  disc, listA ),
                    listA.contains( disc )                                                          // equal element has to be in old list
                );
                //
                assertTrue(
                    String.format( "disc NOT removed from list -> %s still in %s",  disc, listA ),
                    listA.remove( disc )                                                            // removing equal (old) element in old list
                );
                //=> (old) disc was removed from old list - at least success was reported
                //
                // cross check
                assertTrue(
                    String.format( "disc is NOT missing -> %s (%d>=%d)",  disc, listA.getSize(), testSetSize ),
                    1+listA.getSize() == testSetSize                                                // removed element has to be gone
                );
                //=> removed element is missing - at least list size shrinked by one
                // NOW it is checked TWICE -> old element was removed
                //
                final int randomPositionA = (int)( 1+Math.random()*listA.getSize() );               // just some valid position in new list
                listA.putNo( randomPositionA, disc );                                               // inserting equal (new) element in old list
                assertTrue(
                    String.format( "disc NOT added -> %s (%d<%d)",  disc, listA.getSize(), testSetSize ),
                    listA.getSize() == testSetSize                                                  // added element has to increase list size
                );
                //=> at least list size grew by one
            }//while
            //=> listB empty (and all elements replaced in listA)
            
            // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
            // check replacement
            //
            // check if number of elements differ
            assertTrue(
                String.format( "expectation NOT met -> %d != %d",  testSetSize, listA.getSize() ),
                testSetSize == listA.getSize()                                                      // number of elements has to be unchanged
            );
            //=> number of elements unchanged
            //
            // check each element if it was replaced
            for( final Disc disc : testData[testSetId] ){                                           // check for each new element
                // search element in old list
                int index=0;
                while(( index < listA.getSize() )  &&  ( ! disc.equals( listA.getNo(index)))){ index++; }
                assertTrue(
                    String.format( "disc NOT found in list -> %d => %s NOT in (%d : %s)",  index, disc, listA.getSize(), listA ),
                    index < listA.getSize()                                                         // element has to be still there
                );
                //=> index points at element found
                //
                assertTrue(
                    String.format( "same identity expected (%d), but %s != %s",  index, disc, listA.getNo(index) ),
                    disc == listA.getNo(index)                                                     // element is the new one
                );
                //=> element in listA is from listB respectively new test set
            }//for
            //=> all elements replaced in listA
        }//for
    }//method()
    
    
    
    
    
    //##########################################################################
    
    private static void checkListAgainstArray( final MyList<Disc> list,  final Disc[] refArray,  final String errorExplanation ){
        assertEquals(
            String.format( "%s -> list and reference array differ in size %d != %d",  errorExplanation, list.getSize(), refArray.length ),
            list.getSize(),
            refArray.length
        );
        for( int index=refArray.length; --index>=0; ){
            assertEquals(
                String.format( "%s -> element %s is NOT equal reference element %s",  errorExplanation, list.getNo(index), refArray[index] ),
                list.getNo(index),
                refArray[index]
            );
        }//for
    }//method()
    //
    private static void checkListAgainstList( final MyList<Disc> list,  final MyList<Disc> refList,  final String errorExplanation ){
        assertEquals(
            String.format( "%s -> lists differ in size %d != %d",  errorExplanation, list.getSize(), refList.getSize() ),
            list.getSize(),
            refList.getSize()
        );
        for( int index=refList.getSize(); --index>=0; ){
            assertEquals(
                String.format( "%s -> element %s is NOT equal reference element %s",  errorExplanation, list.getNo(index), refList.getNo(index) ),
                list.getNo(index),
                refList.getNo(index)
            );
        }//for
    }//method()
    
    private static void fillListWithTestData( final MyList<Disc> list,  final int testSetId ){
        list.clear();
        for( final Disc disc : testData[testSetId] ){
            list.putL( disc );
        }//for
        assertTrue(
            String.format( "unexpected number of elements in list -> %d != %d",  testData[testSetId].length, list.getSize() ),
            testData[testSetId].length == list.getSize()
        );
    }//method()
        
    private static Map<Disc,Integer> toStateMap( final MyList<Disc> list ){
        final Map<Disc,Integer> stateMap = new HashMap<Disc,Integer>();
        for ( int index=list.getSize(); --index>=0; ){
            final Disc disc = list.getNo( index );
            final Integer cnt = stateMap.get( disc );
            stateMap.put( disc, ( null==cnt ) ? 1 : cnt+1 );
        }//for
        return stateMap;
    }//method()
    
    
    
    
    
    static {
        // print test header on console
        System.out.printf( "TI1-P1 (WS17/18):\n" );
        System.out.printf( "=================\n" );
        System.out.printf( "\n" );
        System.out.printf( "Test frame version: 171203 ,  v1.00\n" );
        System.out.printf( "Java: %s bzw. %s\n",  System.getProperty( "java.specification.version" ), System.getProperty( "java.version" ) );
        System.out.printf( "Path: %s\n",  UnitTestFrame.class.getProtectionDomain().getCodeSource().getLocation().getPath() );
        System.out.printf( "\n\n" );
        //
        // setup test data
        testData = new Disc[][] {
            {   new CD( "Sing mit Heino", Content.AUDIO, "Heino" ),
                new DVD( "Augsburger Puppenkiste: Urmel aus dem Eis – Gold Edition", MOVIE, PAL ),
                new CD( "IV", AUDIO, "Led Zeppelin" ),
                new CD( "Quadrophenia", AUDIO, "Who" ),
                new DVD( "Blade Runner", MOVIE, PAL ),
                new DVD( "Cadillac Man", MOVIE, PAL ),
                new DVD( "Sin City - Uncut", MOVIE, NTSC ),
                new DVD( "Gone with the Wind", MOVIE, PAL ),
                new DVD( "David Bowie: Best of Bowie", VIDEO, PAL ),
                new DVD( "The Good, the Bad and the Ugly", MOVIE, PAL ),
                new DVD( "Guardians of the Galaxy", MOVIE, PAL ),
                new DVD( "John Carter", MOVIE, PAL ),
                new DVD( "Silver Streak", MOVIE, NTSC ),
                new DVD( "After Hours", MOVIE, NTSC ),
                new DVD( "Flesh + Blood", MOVIE, NTSC ),
                new DVD( "The Prestige", MOVIE, PAL ),
                new DVD( "The Last King of Scotland", MOVIE, PAL ),
                new DVD( "The Mexican", MOVIE, PAL ),
                new DVD( "Invasion of the Body Snatchers", MOVIE, PAL ),
                new DVD( "Hotel Rwanda", MOVIE, PAL ),
                new CD( "XX", AUDIO, "XX" ),
                new CD( "If You Wait", AUDIO, "London Grammar" ),
                new CD( "Days of Future Passed", AUDIO, "Moody Blues" ),
                new DVD( "Snatch", MOVIE, PAL ),
                new CD( "Traumstation", AUDIO, "Cosa Rosa" ),
                new DVD( "King Kong", MOVIE, PAL ),
                new DVD( "Plunkett & Macleane", MOVIE, PAL ),
                new DVD( "Shrek", MOVIE, PAL ),
                new CD( "IV", AUDIO, "Toto" ),
                new DVD( "The man who would be King", MOVIE, PAL ),
                new DVD( "Night of the Demon", MOVIE, PAL ),
                new DVD( "Galaxy Quest", MOVIE, PAL ),
                new DVD( "johnny got his gun", MOVIE, PAL ),
                new DVD( "Emperor of the North Pole", MOVIE, NTSC ),
                new CD( "Crime of the Century", AUDIO, "Supertramp" )
            },
            //------------------------------------------------------------------
            {   new CD( "Sing mit Heino", AUDIO, "Heino" ),
                new DVD( "Augsburger Puppenkiste: Urmel aus dem Eis – Gold Edition", MOVIE, PAL ),
                new CD( "IV", AUDIO, "Led Zeppelin" ),
                new CD( "Quadrophenia", AUDIO, "Who" ),
                new DVD( "Blade Runner", MOVIE, PAL ),
                new DVD( "Cadillac Man", MOVIE, PAL ),
                new DVD( "Sin City - Uncut", MOVIE, NTSC ),
                new DVD( "Gone with the Wind", MOVIE, PAL ),
                new DVD( "David Bowie: Best of Bowie", VIDEO, PAL ),
                new DVD( "The Good, the Bad and the Ugly", MOVIE, PAL ),
                new DVD( "Guardians of the Galaxy", MOVIE, PAL ),
                new DVD( "John Carter", MOVIE, PAL ),
                new DVD( "Silver Streak", MOVIE, NTSC ),
                new DVD( "After Hours", MOVIE, NTSC ),
                new DVD( "Flesh + Blood", MOVIE, NTSC ),
                new DVD( "The Prestige", MOVIE, PAL ),
                new DVD( "The Last King of Scotland", MOVIE, PAL ),
                new DVD( "The Mexican", MOVIE, PAL ),
                new DVD( "Invasion of the Body Snatchers", MOVIE, PAL ),
                new DVD( "Hotel Rwanda", MOVIE, PAL ),
                new CD( "XX", AUDIO, "XX" ),
                new CD( "If You Wait", AUDIO, "London Grammar" ),
                new CD( "Days of Future Passed", AUDIO, "Moody Blues" ),
                new DVD( "Snatch", MOVIE, PAL ),
                new CD( "Traumstation", AUDIO, "Cosa Rosa" ),
                new DVD( "King Kong", MOVIE, PAL ),
                new DVD( "Plunkett & Macleane", MOVIE, PAL ),
                new DVD( "Shrek", MOVIE, PAL ),
                new CD( "IV", AUDIO, "Toto" ),
                new DVD( "The man who would be King", MOVIE, PAL ),
                new DVD( "Night of the Demon", MOVIE, PAL ),
                new DVD( "Galaxy Quest", MOVIE, PAL ),
                new DVD( "johnny got his gun", MOVIE, PAL ),
                new DVD( "Emperor of the North Pole", MOVIE, NTSC ),
                new CD( "Crime of the Century", AUDIO, "Supertramp" )
            },
            //------------------------------------------------------------------
            {   new CD( "Sing mit Heino", AUDIO, "Heino" ),
                new DVD( "Augsburger Puppenkiste: Urmel aus dem Eis – Gold Edition", MOVIE, PAL ),
                new CD( "IV", AUDIO, "Led Zeppelin" ),
                new CD( "Quadrophenia", AUDIO, "Who" ),
                new DVD( "Blade Runner", MOVIE, PAL ),
                new DVD( "Cadillac Man", MOVIE, PAL ),
                new DVD( "Sin City - Uncut", MOVIE, NTSC ),
                new DVD( "Gone with the Wind", MOVIE, PAL ),
                new DVD( "David Bowie: Best of Bowie", VIDEO, PAL ),
                new DVD( "The Good, the Bad and the Ugly", MOVIE, PAL ),
                new DVD( "Guardians of the Galaxy", MOVIE, PAL ),
                new DVD( "John Carter", MOVIE, PAL ),
                new DVD( "Silver Streak", MOVIE, NTSC ),
                new DVD( "After Hours", MOVIE, NTSC ),
                new DVD( "Flesh + Blood", MOVIE, NTSC ),
                new DVD( "The Prestige", MOVIE, PAL ),
                new DVD( "The Last King of Scotland", MOVIE, PAL ),
                new DVD( "The Mexican", MOVIE, PAL ),
                new DVD( "Invasion of the Body Snatchers", MOVIE, PAL ),
                new DVD( "Hotel Rwanda", MOVIE, PAL ),
                new CD( "XX", AUDIO, "XX" ),
                new CD( "If You Wait", AUDIO, "London Grammar" ),
                new CD( "Days of Future Passed", AUDIO, "Moody Blues" ),
                new DVD( "Snatch", MOVIE, PAL ),
                new CD( "Traumstation", AUDIO, "Cosa Rosa" ),
                new DVD( "King Kong", MOVIE, PAL ),
                new DVD( "Plunkett & Macleane", MOVIE, PAL ),
                new DVD( "Shrek", MOVIE, PAL ),
                new CD( "IV", AUDIO, "Toto" ),
                new DVD( "The man who would be King", MOVIE, PAL ),
                new DVD( "Night of the Demon", MOVIE, PAL ),
                new DVD( "Galaxy Quest", MOVIE, PAL ),
                new DVD( "johnny got his gun", MOVIE, PAL ),
                new DVD( "Emperor of the North Pole", MOVIE, NTSC ),
                new CD( "Crime of the Century", AUDIO, "Supertramp" )
            }
        };
    };
    //
    final private static Disc[][] testData;
    //
    final private static int numberOfTestSets = 3;                              // as coded above - the number of test "sets" resp. test arrays resp. testData.length
    
    final private static int commonLimit = 4_000;                               // timeout resp. max. number of ms for test

}//class
