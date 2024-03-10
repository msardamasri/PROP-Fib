package test.subgrup14_1.mastermind;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import test.subgrup14_1.mastermind.testsUnitaris.AlgoritmeGeneticTest;
import test.subgrup14_1.mastermind.testsUnitaris.CodebreakerTest;
import test.subgrup14_1.mastermind.testsUnitaris.CodemakerTest;
import test.subgrup14_1.mastermind.testsUnitaris.InfoPartidaTest;
import test.subgrup14_1.mastermind.testsUnitaris.PartidaTest;
import test.subgrup14_1.mastermind.testsUnitaris.UsuariTest;

@RunWith(value = Suite.class)
@SuiteClasses(value = {PartidaTest.class, InfoPartidaTest.class, UsuariTest.class, AlgoritmeGeneticTest.class, CodemakerTest.class, CodebreakerTest.class})
public class TestSuiteModels {}