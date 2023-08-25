# choreography-to-PRISM


# TO-DO List
- Finish Prism semantics as rules, following the semantics in this [paper](https://www.prismmodelchecker.org/doc/semantics.pdf) -- MARCO, for next time
  * After a first discussion, the semantics of a PRISM program is standard state change semantics, where state is a set of variable assignments. Steps are defined by a set of "rated" actions that are executed if a guard is satisfied. 
- Finalise the formal definition of the choreographic language 
  * First Step: Adele finishes implementation (test on examples to see if syntax is good enough) -- Adele for next time
  * Write formal syntax in Tex -- next time
- based on Prism semantics, define semantics for the choreographic language -- next time 
  * The choreographic semantics should simulate what PRISM does. But in this case, the choreographic syntax is giving the order of state updates (assignments) that need to be done. Note that this corresponds to guard evaluations and state updates generated by the projection. 
- define mapping from choreographic language to PRISM, based on Adele's implementation -- next time

