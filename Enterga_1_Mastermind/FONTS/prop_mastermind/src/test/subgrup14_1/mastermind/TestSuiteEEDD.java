package test.subgrup14_1.mastermind;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import test.subgrup14_1.mastermind.testsUnitaris.InformacioPartidaTest;
import test.subgrup14_1.mastermind.testsUnitaris.InformacioUsuariTest;
import test.subgrup14_1.mastermind.testsUnitaris.PairTest;
import test.subgrup14_1.mastermind.testsUnitaris.PartidaEnJocTest;

@RunWith(value = Suite.class)
@SuiteClasses(value = {InformacioPartidaTest.class, InformacioUsuariTest.class, PairTest.class, PartidaEnJocTest.class})
public class TestSuiteEEDD {}