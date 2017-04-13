package com.example.hnTea.rxjava;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Created by 太能 on 2016/12/26.
 */
public class RxEventBus {
    private final Subject<Object,Object> mSubjectBus;

    public RxEventBus() {
        mSubjectBus = new SerializedSubject<>(PublishSubject.create());
    }

    private static class SingletonFactory {
        private static volatile RxEventBus mDefaultRxEventBus = new RxEventBus();
    }

    public static RxEventBus getDefault() {
        return SingletonFactory.mDefaultRxEventBus;
    }


    public void postEvent(Object event) {
        mSubjectBus.onNext(event);
    }


    public Observable<Object> onReceiveEvent() {
        return mSubjectBus;
    }

    // 根据传递的 eventType 类型返回特定类型(eventType)的 被观察者
    public <T> Observable<T> toObservable (Class<T> eventType) {
        return mSubjectBus.ofType(eventType);
//         ofType = filter + cast
//        return bus.filter(new Func1<Object, Boolean>() {
//            @Override
//            public Boolean call(Object o) {
//                return eventType.isInstance(o);
//            }
//        }) .cast(eventType);
    }
}
