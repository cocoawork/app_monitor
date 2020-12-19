package top.cocoawork.monitor.service.impl.base;

import top.cocoawork.monitor.service.api.exception.ServiceException;
import top.cocoawork.monitor.common.BeanUtil;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author cocoawork
 */
abstract public class BaseServiceImpl<D, DTO> {

    private Class<D> dClass = getClass(0);
    private Class<DTO> dtoClass = getClass(1);


    public void d2dto(D d, DTO dto) {
        BeanUtil.copyProperties(d, dto);
    }

    public void dto2d(DTO dto, D d) {
        BeanUtil.copyProperties(dto, d);
    }

    public DTO d2dto(D d) {
        try {
            DTO dto = dtoClass.newInstance();
            d2dto(d, dto);
            return dto;
        } catch (InstantiationException e) {
            throw new ServiceException(e);
        } catch (IllegalAccessException e) {
            throw new ServiceException(e);
        }
    }

    public D dto2d(DTO dto) {
        try {
            D d = dClass.newInstance();
            dto2d(dto, d);
            return d;
        } catch (InstantiationException e) {
            throw new ServiceException(e);
        } catch (IllegalAccessException e) {
            throw new ServiceException(e);
        }
    }


    private Class getClass(int index) {
        Type superclass = getClass().getGenericSuperclass();
        ParameterizedType parameterizedType = null;
        if (superclass instanceof ParameterizedType) {
            parameterizedType = (ParameterizedType)superclass;
            Type[] types = parameterizedType.getActualTypeArguments();
            if (types != null && types.length >= (1+index) ) {
                return (Class) types[index];
            }

        }
        return null;
    }

}
