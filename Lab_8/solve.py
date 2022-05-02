import angr
import claripy

proj = angr.Project('./target');

sym_arg_size = 28
sym_arg = claripy.BVS('sym_arg', 8*sym_arg_size)

argv = ['target']
argv.append(sym_arg)
state = proj.factory.entry_state(args=argv)

simgr = proj.factory.simulation_manager(state)

avoid_addr = []
find_addr = 0x401060
simgr.explore(find=find_addr, avoid=avoid_addr)

found = simgr.found[0]
ans = found.solver.eval(sym_arg, cast_to=bytes)
print(ans)
