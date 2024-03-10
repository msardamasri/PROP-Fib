package test.subgrup14_1.mastermind;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import test.subgrup14_1.mastermind.testsUnitaris.AlgoritmeFiveGuessTest;
import test.subgrup14_1.mastermind.testsUnitaris.AlgoritmeGeneticTest;
import test.subgrup14_1.mastermind.testsUnitaris.CodebreakerTest;
import test.subgrup14_1.mastermind.testsUnitaris.CodemakerTest;
import test.subgrup14_1.mastermind.testsUnitaris.InfoPartidaTest;
import test.subgrup14_1.mastermind.testsUnitaris.InformacioPartidaTest;
import test.subgrup14_1.mastermind.testsUnitaris.InformacioUsuariTest;
import test.subgrup14_1.mastermind.testsUnitaris.PairTest;
import test.subgrup14_1.mastermind.testsUnitaris.PartidaEnJocTest;
import test.subgrup14_1.mastermind.testsUnitaris.PartidaTest;
import test.subgrup14_1.mastermind.testsUnitaris.UsuariTest;
import test.subgrup14_1.mastermind.testsUnitaris.UtilsTest;

@RunWith(value = Suite.class)
@SuiteClasses(value = { AlgoritmeGeneticTest.class, InfoPartidaTest.class, InformacioPartidaTest.class, InformacioUsuariTest.class, PairTest.class, PartidaEnJocTest.class, PartidaTest.class, UsuariTest.class, UtilsTest.class, AlgoritmeFiveGuessTest.class, CodebreakerTest.class, CodemakerTest.class, })
public class TestSuiteTotal {}
