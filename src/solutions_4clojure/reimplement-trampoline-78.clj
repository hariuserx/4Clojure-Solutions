(fn [f x]
  (let [val (f x)]
    ((fn helper [res]
       (if (fn? res)
         (helper (res))
         res)) val)))