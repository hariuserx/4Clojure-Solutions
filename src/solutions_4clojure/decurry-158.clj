(fn decurry [f]
  (fn [& vals]
    ((fn helper [vals res]
       (if (fn? res)
         (helper (drop 1 vals)
                 (res (first vals)))
         res))
     vals
     f)))