//package es.us.isa.ideas.module.cplex;
//
//import ilog.concert.IloConstraint;
//import ilog.concert.IloException;
//import ilog.concert.IloIntVar;
//import ilog.concert.IloSymbolMap;
//import ilog.concert.cppimpl.IloEnv;
//import ilog.cp.IloCP;
//import ilog.opl.IloOplElement;
//import ilog.opl.IloOplErrorHandler;
//import ilog.opl.IloOplFactory;
//import ilog.opl.IloOplModel;
//import ilog.opl.IloOplModelDefinition;
//import ilog.opl.IloOplModelSource;
//import ilog.opl.IloOplSettings;
//
//import java.util.Iterator;
//
//public class AppTest {
//	public static void main(String[] args) {
//
//		// testModel();
//		testImportModel("colors.mod");
//		// testImportModelCPO("colors.cpo");
//
//	}
//
//	static void testModel() {
//		try {
//			IloCP cp = new IloCP();
//			IloIntVar InputError = cp.intVar(0, 199);
//			InputError.setName("InputError");
//			// IloConstraint c1 = cp.le(InputError, 2);
//			IloConstraint c2 = cp.ge(InputError, 200);
//			// IloConstraint c = cp.and(c1, c2);
//			cp.add(c2);
//
//			cp.setParameter(IloCP.IntParam.ConflictRefinerOnVariables,
//					IloCP.ParameterValues.On);
//
//			cp.solve();
//			System.out.println(cp.getConflict(InputError));
//
//			if (cp.refineConflict()) {
//				cp.writeConflict(cp.output());
//
//			} else if (cp.solve()) {
//				System.out.println("\nSolution:");
////				IloIntVar[] vars = cp.getAllIloIntVars();
////				for (int i = 0; i < vars.length; i++)
////					System.out.println("\n\t" + vars[i].getName() + ": "
////							+ (int) cp.getValue(vars[i]));
//			}
//
//		} catch (IloException e) {
//			e.printStackTrace();
//		}
//	}
//
//	static void testImportModel(String filename) {
//		try {
//			IloEnv env = new IloEnv();
//			IloOplFactory oplF = new IloOplFactory();
//			IloOplErrorHandler errHandler = oplF.createOplErrorHandler();
//			IloOplModelSource modelSource = oplF.createOplModelSource(filename);
//			IloOplSettings settings = new IloOplSettings(env, errHandler);
//			IloOplModelDefinition def = oplF.createOplModelDefinition(
//					modelSource, settings);
//
//			IloCP cp = oplF.createCP();
//			cp.setParameter(IloCP.IntParam.ConflictRefinerOnVariables,
//					IloCP.ParameterValues.On);
//			IloOplModel opl = oplF.createOplModel(def, cp);
//			opl.generate();
//
//			System.out.println("Has solution: " + cp.solve());
//
//			if (cp.refineConflict()) {
//				System.out
//						.println("----------------------- CONFLICTS -----------------------");
//				opl.printConflict(System.out);
//
//			} else if (cp.solve()) {
//				opl.printSolution(System.out);
//
//				if (filename.startsWith("colors")) {
//					IloOplElement namesElement = opl.getElement("Names");
//					IloSymbolMap names = namesElement.asSymbolMap();
//					for (Iterator<IloOplElement> it = opl.getElementIterator(); it
//							.hasNext();) {
//						IloOplElement e = it.next();
//						try {
//							if (e.isDecisionVariable()) {
//								IloIntVar var = e.asIntVar();
//								System.out.println("\n\t"
//										+ e.getName()
//										+ ": "
//										+ names.get((int) opl.getCP().getValue(
//												var)));
//							}
//						} catch (Exception ex) {
//							// TODO: handle exception
//						}
//
//					}
//				}
//			}
//
//			opl.end();
//		} catch (IloException e) {
//			e.printStackTrace();
//		}
//	}
//
//}