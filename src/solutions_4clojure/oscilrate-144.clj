(fn oscilrate [v & fs]
  (let [c (count fs)]
    ((fn helper [counter value fs]
       (lazy-seq (cons value
                       (helper (inc counter)
                               ((nth fs
                                     (rem counter c))
                                value)
                               fs))))
     0
     v
     fs)))