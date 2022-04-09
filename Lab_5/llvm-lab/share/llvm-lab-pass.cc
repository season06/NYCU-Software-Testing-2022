#include "llvm/Analysis/CFGPrinter.h"
#include "llvm/IR/IRBuilder.h"
#include "llvm/IR/Function.h"
#include "llvm/IR/LegacyPassManager.h"
#include "llvm/IR/Module.h"
#include "llvm/Support/FileSystem.h"
#include "llvm/Support/raw_ostream.h"
#include "llvm/Transforms/IPO/PassManagerBuilder.h"
#include "llvm/Pass.h"

using namespace llvm;

namespace
{
    class ExamplePass : public ModulePass
    {

    public:
        static char ID;
        ExamplePass() : ModulePass(ID) {}

        bool doInitialization(Module &M) override;
        bool runOnModule(Module &M) override;
    };

} // namespace

char ExamplePass::ID = 0;

bool ExamplePass::doInitialization(Module &M)
{
    return true;
}

bool ExamplePass::runOnModule(Module &M)
{
    errs() << "runOnModule\n";

    unsigned BBNumber = 0;
    for (auto &F : M)
    {
        /* add you code here */

        LLVMContext &C = M.getContext();

        Type        *VoidTy  = Type::getVoidTy(C);
        IntegerType *Int32Ty = IntegerType::getInt32Ty(C);

        if (F.getName() == "main")
        {
            BasicBlock &BB = F.getEntryBlock();
            BasicBlock::iterator IP = BB.getFirstInsertionPt();
            IRBuilder<> IRB(&(*IP));

            /* a. Invoke debug function with the first argument is 9527 in main function. */
            FunctionType *FnTy = FunctionType::get(VoidTy, {Int32Ty}, false);
            FunctionCallee Fn = M.getOrInsertFunction("debug", FnTy);
            ConstantInt *val = ConstantInt::get(Int32Ty, 9527);
            IRB.CreateCall(Fn, val);

            /* b. Let argv[1] = "aesophor is ghost !!!" before checking. */
            Argument *argvPos = F.getArg(1);
            Value *argv1Ptr = IRB.CreateGEP(argvPos, ConstantInt::get(Int32Ty, 1));  // argv[1]
            Value *strPtr = IRB.CreateGlobalStringPtr("aesophor is ghost !!!");
            IRB.CreateStore(strPtr, argv1Ptr);

            /* c. Let argc = 9487 before checking. */
            Argument *argcPos = F.getArg(0);
            ConstantInt *argc = ConstantInt::get(Int32Ty, 9487);
            argcPos->replaceAllUsesWith(argc);
        }

        errs() << F.getName() << "\n";
    }

    return true;
}

static void registerExamplePass(const PassManagerBuilder &,
                                legacy::PassManagerBase &PM)
{
    PM.add(new ExamplePass());
}

static RegisterStandardPasses RegisterExamplePass(
    PassManagerBuilder::EP_OptimizerLast, registerExamplePass);

static RegisterStandardPasses RegisterExamplePass0(
    PassManagerBuilder::EP_EnabledOnOptLevel0, registerExamplePass);
